package com.github.jreddit.request.action.submit;

import com.github.jreddit.parser.entity.Thing;
import com.github.jreddit.request.RedditPostRequest;
import com.github.jreddit.request.entity.SubmissionEntity;
import org.json.simple.JSONObject;

public class CommentRequest extends RedditPostRequest {
    private static final String API_TYPE = "json";

    private static final boolean RETURN_RICHTEXT_JSON = true;

    private static String jsonRichText;

    private String markdownText;

    /** Endpoint format. */
    private static final String ENDPOINT_FORMAT = " /api/comment";

    public CommentRequest(Thing parent, JSONObject json) {
        this.addBodyParameter("parent", parent.getFullName());
        this.addBodyParameter("richtext_json", json.toJSONString());
        this.addBodyParameter("api_type", API_TYPE);
    }

    public CommentRequest(Thing parent, String text) {
        this.addBodyParameter("parent", parent.getFullName());
        this.addBodyParameter("text", text);
        this.addBodyParameter("api_type", API_TYPE);
    }

    public CommentRequest(SubmissionEntity entity, String text)  {
        this.addBodyParameter("parent", entity.getFullName());
        this.addBodyParameter("text", text);
        this.addBodyParameter("api_type", API_TYPE);
        this.addBodyParameter("return_rtjson", "true");
    }

    @Override
    public String generateRedditURI() {
        return ENDPOINT_FORMAT;
    }
}
