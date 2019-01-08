//package com.hu.security.core.social.qq.connet;
//
//import com.hu.security.core.social.qq.api.QQ;
//import com.hu.security.core.social.qq.api.QQImpl;
//import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
//import org.springframework.social.oauth2.OAuth2Operations;
//
//public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
//
//    private String appId;
//
//    /**
//     * Create a new {@link OAuth2ServiceProvider}.
//     *
//     * @param oauth2Operations the OAuth2Operations template for conducting the OAuth 2 flow with the provider.
//     */
//    public QQServiceProvider(OAuth2Operations oauth2Operations) {
//        super(oauth2Operations);
//    }
//
//    @Override
//    public QQ getApi(String accessToken) {
//        return new QQImpl(accessToken, appId);
//    }
//}
