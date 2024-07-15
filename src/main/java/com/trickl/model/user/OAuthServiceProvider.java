package com.trickl.model.user;

/** The OAuth service provider. */
@SuppressWarnings("AbbreviationAsWordInName")
public enum OAuthServiceProvider {
  FACEBOOK("http://www.facebook.com/profile.php?id={id}"),
  GOOGLE("http://plus.google.com/{id}"),
  TWITTER("http://twitter.com/{id}");

  private final String profileUriTemplate;

  OAuthServiceProvider(String profileUriTemplate) {
    this.profileUriTemplate = profileUriTemplate;
  }

  public String getProfileUriTemplate() {
    return profileUriTemplate;
  }
}
