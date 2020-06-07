package com.github.jreddit.parser.listing;


import com.github.jreddit.parser.entity.UserInfo;
import com.github.jreddit.parser.exception.RedditParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserInfoParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubredditsListingParser.class);

    protected static final JSONParser JSON_PARSER = new JSONParser();


    /**
     * Parse JSON received from reddit into a user object
     * This parser expects the JSON to be user info from the /user/username/about endpoint.
     *
     * @param jsonText JSON Text
     * @return Parsed user info
     *
     * @throws org.json.simple.parser.ParseException
     */
    public UserInfo parse(String jsonText) throws RedditParseException {

        UserInfo userInfo;
        try {

            // Parse the resonse
            Object response = JSON_PARSER.parse(jsonText);

            // Cast to a JSON object
            JSONObject object = (JSONObject) response;

            // Grab the user data from the response
            JSONObject userInfoJson = ((JSONObject) object.get("data"));

            userInfo = new UserInfo(userInfoJson);

        } catch (ParseException pe) {
            throw new RedditParseException(pe);
        }

        // Return the user info
        return userInfo ;

    }
}
