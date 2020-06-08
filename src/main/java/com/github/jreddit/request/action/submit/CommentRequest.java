package com.github.jreddit.request.action.submit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jreddit.parser.entity.Thing;
import com.github.jreddit.request.RedditPostRequest;
import org.json.simple.JSONObject;

public class CommentRequest extends RedditPostRequest {
    @JsonProperty("api_type")
    private static final String API_TYPE = "json";

    @JsonProperty("return_rtjson")
    private static final boolean RETURN_RICHTEXT_JSON = true;

    @JsonProperty("richtext_json")
    private static String jsonRichText;

    @JsonProperty("parent")
    private Thing parent;

    @JsonProperty("text")
    private String markdownText;

    /** Endpoint format. */
    private static final String ENDPOINT_FORMAT = " /api/comment";

    public CommentRequest(Thing parent, JSONObject json) {
        this.addBodyParameter("parent", parent.getFullName());
        this.addBodyParameter("richtext_json", json.toJSONString());
    }

    public CommentRequest(Thing parent, String text) {
        this.addBodyParameter("parent", parent.getFullName());
        this.addBodyParameter("text", text);
    }

    @Override
    public String generateRedditURI() {
        return ENDPOINT_FORMAT;
    }
}
