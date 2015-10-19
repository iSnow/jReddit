package com.github.jreddit.parser.entity;

import org.json.simple.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UserInfoTest {

    @SuppressWarnings("unchecked")
    @Test
    public void testUserInfoFields(){
        String userId = "isjs2k2";
        String username = "testusername";
        String modhash = "sdawsdfKSF78sdsd";
        long commentKarma = 15244;
        long linkKarma = 12314;
        boolean isMod = false;
        boolean hasModMail = false;
        boolean hasMail = true;
        boolean hasVerifiedEmail = true;
        boolean isGold = false;
        double created = 1309455951;
        double createdUTC = 1309452351;
        boolean isFriend = false;
        boolean over18 = true;

        JSONObject userData = new JSONObject();
        userData.put("name", username);
        userData.put("id", userId);
        userData.put("modhash", modhash);
        userData.put("comment_karma", commentKarma);
        userData.put("link_karma", linkKarma);
        userData.put("is_mod", isMod);
        userData.put("has_mod_mail", hasModMail);
        userData.put("has_mail", hasMail);
        userData.put("has_verified_email", hasVerifiedEmail);
        userData.put("is_gold", isGold);
        userData.put("created", created);
        userData.put("created_utc", createdUTC);
        userData.put("is_friend", isFriend);
        userData.put("over_18", over18);

        UserInfo userInfo = new UserInfo(userData);

        assertEquals(userInfo.getName(), username);
        assertEquals(userInfo.getId(), userId);
        assertEquals(userInfo.getModhash(), modhash);
        assertEquals(userInfo.getCommentKarma(), commentKarma);
        assertEquals(userInfo.getLinkKarma(), linkKarma);
        assertEquals(userInfo.isMod(), isMod);
        assertEquals(userInfo.getHasModMail(), hasModMail);
        assertEquals(userInfo.getHasMail(), hasMail);
        assertEquals(userInfo.isHasVerifiedEmail(), hasVerifiedEmail);
        assertEquals(userInfo.isGold(), isGold);
        assertEquals(userInfo.getCreated(), created, 0);
        assertEquals(userInfo.getCreatedUTC(), createdUTC, 0);
        assertEquals(userInfo.isFriend(), isFriend);
        assertEquals(userInfo.getOver18(), over18);
    }


}
