package core.lib.network.base;

import androidx.annotation.VisibleForTesting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import core.lib.base.BaseApp;
import core.lib.network.response.BaseResponse;
import core.lib.utils.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import kmobile.logger.BuildConfig;
import lombok.Getter;
import lombok.Setter;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public abstract class BaseNetwork<D, R extends retrofit2.Response, T extends BaseResponse> implements Interceptor {

    private static OkHttpClient okHttpClient;
    private static Gson         gson;
    @Getter
    @Setter
    private static String       token = "";

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = getUnsafeOkHttpClient();
        }
        return okHttpClient;
    }

    public static void attachInterceptor(Interceptor interceptor) {
        okHttpClient = getOkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build();
    }

    public static Gson getGson() {
//        if (gson == null) {
////            gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
////        }
        return BaseApp.getGson();
    }

    protected D                    mData;
    protected AuthorizationHandler mAuthorizationHandler;
    protected Observable<R>        mObservable;

    @VisibleForTesting
    protected HttpUrl mHttpUrl;

    /**
     * Default constructor just in case the task does not required to saveToCache any data to the database.
     */
    public BaseNetwork() {
    }

    @VisibleForTesting
    protected BaseNetwork(HttpUrl httpUrl, AuthorizationHandler authorizationHandler) {
        mHttpUrl = httpUrl;
        mAuthorizationHandler = authorizationHandler;
    }

    public BaseNetwork(AuthorizationHandler authorizationHandler) {
        mAuthorizationHandler = authorizationHandler;
    }

    protected String getBaseEndpointUrl() {
        return ConstNetwork.BASE_URL;
    }

    public Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
//        builder.addConverterFactory(GsonConverterFactory.create(getGson()));
        builder.addConverterFactory(ScalarsConverterFactory.create());
        builder.client(getOkHttpClient().newBuilder().addNetworkInterceptor(this).build());
        if (mHttpUrl != null) {
            builder.baseUrl(mHttpUrl);
        }
        else {
            builder.baseUrl(getBaseEndpointUrl());
        }
        return builder.build();
    }

    public D getData() {
        return mData;
    }

    public Observable<R> getObservable() {
        return mObservable;
    }

    public AuthorizationHandler getAuthorizationHandler() {
        return mAuthorizationHandler;
    }

    public BaseNetwork setData(D data) {
        if (BuildConfig.DEBUG_MODE && data != null) {
            Log.i("LOG >> " + new GsonBuilder().setPrettyPrinting().create().toJson(data));
        }
        mData = data;
        return this;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (mAuthorizationHandler != null) {
            request = mAuthorizationHandler.applyAuthorizationHeader(request);
        }
        Response response = chain.proceed(supplyCustomHeader(request));
        if (response.code() == 401 && mAuthorizationHandler != null) {
            mAuthorizationHandler.onUnAuthorized();
        }
        return response;
    }

    /**
     * A method that allow sub-class to provide additional change to the request unlike Retrofit Adapter
     * this method provide a flexibility to add header without modified the adapter.
     *
     * @param request
     * @return
     */
    public Request supplyCustomHeader(Request request) {
        return request;
    }

    public SimpleObserver start(final SimpleObserver simpleObserver) {
        mObservable = Observable.unsafeCreate(new ObservableSource<R>() {
            @Override
            public void subscribe(@NonNull Observer<? super R> observer) {
                try {
                    R result = onExecute(getRetrofit(), mData);
                    if (result != null && result.isSuccessful()) {
                        simpleObserver.onReceiveResult(mData, result);
                    }
                    observer.onNext(result);
                } catch (Exception e) {
                    observer.onError(e);
                }
                observer.onComplete();
            }
        });
        mObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(simpleObserver);
        return simpleObserver;
    }

    @VisibleForTesting
    public R execute() throws Exception {
        return onExecute(getRetrofit(), mData);
    }

    protected abstract R onExecute(Retrofit retrofit, D input) throws Exception;

    public abstract class SimpleObserver extends DisposableObserver<R> {
        @Getter
        @Setter
        private T      responseObject;
        @Getter
        private String responseJson;

        public void onReceiveResult(@NonNull D request, @NonNull R result) throws Exception {
            if (clazzResponse() != null) {
//                responseJson = MyBase64Utils.decode(result.body().toString());
//                Log.i("LOG >> Response JSON : " + responseJson);
                responseObject = (T) getGson().fromJson(getResponseJson(), clazzResponse());
            }
        }

        @Override
        public void onNext(@NonNull R result) {
        }

        @Override
        public void onError(@NonNull Throwable e) {
        }

        @Override
        public void onComplete() {
        }

        public abstract Class<?> clazzResponse();
    }

    /**
     * Trust every server - don't check for any SSL certificate
     *
     * @return
     */
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
//            builder.hostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            });

            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.writeTimeout(30, TimeUnit.SECONDS);
            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
