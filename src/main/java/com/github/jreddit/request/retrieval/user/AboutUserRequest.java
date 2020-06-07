package com.github.jreddit.request.retrieval.user;

import com.github.jreddit.request.retrieval.ListingRequest;


public class AboutUserRequest extends ListingRequest {

    private static final String ENDPOINT_FORMAT = "/user/%s/about";

    String username;

    /**
     * @param username Target username to retrieve info for (e.g. "jRedditBot")
     */
    public AboutUserRequest(String username) {
        this.username = username;
    }

    @Override
    public String generateRedditURI() {
        return String.format(ENDPOINT_FORMAT, username, this.generateParameters());
    }
}
