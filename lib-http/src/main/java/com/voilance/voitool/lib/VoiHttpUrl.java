package com.voilance.voitool.lib;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Set;

import okhttp3.HttpUrl;

public final class VoiHttpUrl {

    HttpUrl mUrl;

    public VoiHttpUrl(@NotNull String url) {
        mUrl = HttpUrl.parse(url);
    }

    public VoiHttpUrl(@NotNull HttpUrl url) {
        mUrl = url;
    }

    public String protocol() {
        return mUrl.scheme();
    }

    public String host() {
        return mUrl.host();
    }

    public int port() {
        return mUrl.port();
    }

    public boolean isHttps() {
        return mUrl.isHttps();
    }

    public URI uri() {
        return mUrl.uri();
    }

    public URL url() {
        return mUrl.url();
    }

    public int querySize() {
        return mUrl.querySize();
    }

    public String query() {
        return mUrl.query();
    }

    public String encodedQuery() {
        return mUrl.encodedQuery();
    }

    public String queryParamenter(String k) {
        return mUrl.queryParameter(k);
    }

    public String queryParameterName(int index) {
        return mUrl.queryParameterName(index);
    }

    public Set<String> queryParameterNames() {
        return mUrl.queryParameterNames();
    }

    public String queryParameterValue(int index) {
        return mUrl.queryParameterValue(index);
    }

    public List<String> queryParameterValues(String k) {
        return mUrl.queryParameterValues(k);
    }

    public int pathSize() {
        return mUrl.pathSize();
    }

    public String encodedPath() {
        return mUrl.encodedPath();
    }

    public List<String> pathSegments() {
        return mUrl.pathSegments();
    }

    public List<String> encodedPathSegments() {
        return mUrl.encodedPathSegments();
    }

    public String redact() {
        return mUrl.redact();
    }

    public VoiHttpUrl resolve(String link) {
        return new VoiHttpUrl(mUrl.resolve(link));
    }

    public String topPrivateDomain() {
        return mUrl.topPrivateDomain();
    }

    public String username() {
        return mUrl.username();
    }

    public String encodedusername() {
        return mUrl.encodedUsername();
    }

    public String password() {
        return mUrl.password();
    }

    public String encodedpassword() {
        return mUrl.encodedPassword();
    }

    public String fragment() {
        return mUrl.fragment();
    }

    public String encodedfragment() {
        return mUrl.encodedFragment();
    }

    public static class Builder {
        private HttpUrl.Builder mBuilder = new HttpUrl.Builder();

        public Builder protocol(String protocol) {
            mBuilder.scheme(protocol);
            return this;
        }

        public Builder host(String host) {
            mBuilder.host(host);
            return this;
        }

        public Builder port(int port) {
            mBuilder.port(port);
            return this;
        }

        public Builder query(String s) {
            mBuilder.query(s);
            return this;
        }

        public Builder encodeQuery(String encodedQuery) {
            mBuilder.encodedQuery(encodedQuery);
            return this;
        }

        public Builder addQueryParameter(String k, String v) {
            mBuilder.addQueryParameter(k, v);
            return this;
        }

        public Builder addEncodedQueryParameter(String k, String v) {
            mBuilder.addEncodedQueryParameter(k, v);
            return this;
        }

        public Builder setQueryParameter(String k, String v) {
            mBuilder.setQueryParameter(k, v);
            return this;
        }

        public Builder setEncodedQueryParameter(String k, String v) {
            mBuilder.setEncodedQueryParameter(k, v);
            return this;
        }

        public Builder removeAllQueryParameters(String k) {
            mBuilder.removeAllQueryParameters(k);
            return this;
        }

        public Builder removeAllEncodedQueryParameters(String k) {
            mBuilder.removeAllEncodedQueryParameters(k);
            return this;
        }

        public Builder encodedPath(String encodedPath) {
            mBuilder.encodedPath(encodedPath);
            return this;
        }

        public Builder addPathSegment(String pathSegment) {
            mBuilder.addPathSegment(pathSegment);
            return this;
        }

        public Builder addPathSegments(String pathSegments) {
            mBuilder.addPathSegments(pathSegments);
            return this;
        }

        public Builder addEncodedPathSegment(String pathSegment) {
            mBuilder.addEncodedPathSegment(pathSegment);
            return this;
        }

        public Builder addEncodedPathSegments(String pathSegments) {
            mBuilder.addEncodedPathSegments(pathSegments);
            return this;
        }

        public Builder setPathSegment(int index, String pathSegment) {
            mBuilder.setPathSegment(index, pathSegment);
            return this;
        }

        public Builder setEncodedPathSegment(int index, String pathSegment) {
            mBuilder.setEncodedPathSegment(index, pathSegment);
            return this;
        }

        public Builder removePathSegment(int index) {
            mBuilder.removePathSegment(index);
            return this;
        }

        public Builder username(String name) {
            mBuilder.username(name);
            return this;
        }

        public Builder encodedUsername(String name) {
            mBuilder.encodedUsername(name);
            return this;
        }

        public Builder password(String password) {
            mBuilder.password(password);
            return this;
        }

        public Builder encodedPassword(String password) {
            mBuilder.encodedPassword(password);
            return this;
        }

        public Builder fragment(String fragment) {
            mBuilder.fragment(fragment);
            return this;
        }

        public Builder encodedFragment(String fragment) {
            mBuilder.encodedFragment(fragment);
            return this;
        }

        public VoiHttpUrl build() {
            return new VoiHttpUrl(mBuilder.build());
        }
    }
}
