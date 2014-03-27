package com.github.jreddit.test.subreddit;

import com.github.jreddit.subreddit.Subreddit;
import com.github.jreddit.subreddit.Subreddits;
import com.github.jreddit.utils.UtilResponse;
import com.github.jreddit.utils.restclient.Response;
import com.github.jreddit.utils.restclient.RestClient;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.jreddit.subreddit.SubredditType.*;
import static com.github.jreddit.testsupport.JsonHelpers.jsonArrayOf;
import static com.github.jreddit.utils.ApiEndpointUtils.SUBREDDITS;
import static com.github.jreddit.utils.ApiEndpointUtils.SUBREDDITS_GET;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class for testing Subreddit-related methods
 *
 * @author Raul Rene Lepsa
 */
public class SubredditTest {

    private RestClient restClient;
    private Subreddits underTest;
    private Response response;

    @Before
    public void setUp() {
        restClient = mock(RestClient.class);
        underTest = new Subreddits(restClient);
    }

    @Test
    public void getSubredditByNameSuccessfully() {
        response = new UtilResponse(null, subredditListingForFunny(), 200);
        when(restClient.get(SUBREDDITS, null)).thenReturn(response);

        Subreddit subreddit = underTest.getSubredditByName("funny");
        assertNotNull(subreddit);
    }

    @Test
    public void getSubredditByNameForUnknownReddit() {
        response = new UtilResponse(null, subredditListingForFunny(), 200);
        when(restClient.get(SUBREDDITS, null)).thenReturn(response);

        Subreddit subreddit = underTest.getSubredditByName("parp");
        assertNull(subreddit);
    }

    @Test
    public void listDefaultReddits() {
        response = new UtilResponse(null, subredditListingForFunny(), 200);
        when(restClient.get(SUBREDDITS, null)).thenReturn(response);

        List<Subreddit> subreddits = underTest.listDefault();
        assertNotNull(subreddits);
        assertTrue(subreddits.size() == 1);
    }


    @Test
    public void testGetNewSubreddits() throws InterruptedException {
        response = new UtilResponse(null, subredditListingForFunny(), 200);
        when(restClient.get(String.format(SUBREDDITS_GET, NEW.getValue()), null)).thenReturn(response);

        List<Subreddit> subreddits = underTest.getSubreddits(NEW);
        assertNotNull(subreddits);
        assertTrue(subreddits.size() == 1);
    }

    @Test
    public void testGetBannedSubreddits() throws InterruptedException {
        response = new UtilResponse(null, subredditListingForFunny(), 200);
        when(restClient.get(String.format(SUBREDDITS_GET, BANNED.getValue()), null)).thenReturn(response);

        List<Subreddit> subreddits = underTest.getSubreddits(BANNED);
        assertNotNull(subreddits);
        assertTrue(subreddits.size() == 1);
    }

    @Test
    public void testGetPopularSubreddits() throws InterruptedException {
        response = new UtilResponse(null, subredditListingForFunny(), 200);
        when(restClient.get(String.format(SUBREDDITS_GET, POPULAR.getValue()), null)).thenReturn(response);

        List<Subreddit> subreddits = underTest.getSubreddits(POPULAR);
        assertNotNull(subreddits);
        assertTrue(subreddits.size() == 1);
    }



    private JSONObject subredditListingForFunny() {

        JSONObject funnyData = new JSONObject();
        funnyData.put("accounts_active", null);
        funnyData.put("comment_score_hide_mins", 0);
        funnyData.put("created", 1201246556.0);
        funnyData.put("created_utc", 1201242956.0);
        funnyData.put("description", "**Welcome to r/Funny:**\n\nYou may only post if you are funny. \n\n-----\n\n# Rules\n*hover for details*\n\n|||\n|:------|:---|\n|1. No reactiongifs or HIFW posts|How I Feel When posts belong in /r/HIFW. Reaction gifs belong in /r/reactiongifs.|\n|2. No posts with their sole purpose being to communicate with another redditor.| [Click for an Example](http://www.reddit.com/lq3uv/). This includes asking for upvotes.|\n|3. No Posts for the specific point of it being your reddit birthday.|Cake day posts are not allowed.|\n|4. Posts which result in harassment of any individual, subreddit, or other entity may be removed at the moderators' discretion.|Posts with titles such as \"I got banned from \\/r/\\_\\_\\_\" or \"This got removed from \\/r/\\_\\_\\_\" are not allowed.|\n|5. No Politics|Anything involving politics or a political figure. Try /r/politicalhumor instead.|\n|6. No Pictures of just text|Make a self post instead. [Example](http://i.imgur.com/2cPuY8C.jpg)|\n|7. No DAE posts|Go to /r/doesanybodyelse|\n|8. No Links to tumblr|Direct links to images hosted on tumblr (ex. 24.media.tumblr.com/img.jpg) are allowed.|\n|9. No URL shorteners|No link shorteners (or HugeURL) in either post links or comments. They will be deleted regardless of intent.|\n|10. No gore or porn (including sexually graphic images).|Other NSFW content must be tagged as such|\n|11. No personal information.|This includes anything hosted on Facebook's servers, as they can be traced to the original account holder.|\n|12.  No memes, rage comics, demotivationals, eCards, or standupshots|Memes belong in /r/adviceanimals, rage comics go to /r/fffffffuuuuuuuuuuuu, demotivationals go to /r/Demotivational, submit eCards to /r/ecards, and standupshots go to /r/standupshots [Image Macros that aren't memes are allowed](http://en.wikipedia.org/wiki/Image_macro#Formats)\n|13. Do not rehost webcomics|Rehosted webcomics will be removed. Please submit a link to the original comic's site, and possibly a mirror in the comments. Unless you are the creator and it's your preference to do so, do not [hotlink](http://en.wikipedia.org/wiki/Inline_linking) the comic if the page it's located on can be linked to directly. Tumblr-exclusive comics are the exception, and may be rehosted, however if the artist's name or watermark are removed, the post will be removed. [(*)](http://www.reddit.com/kqwwx/) [(*)](http://www.reddit.com/kwmk3)|\n|14. No SMS or Social Media Content (including Reddit)|This includes direct linking to reddit threads, reddit comments, other subreddits, facebook profiles, twitter profiles, tweets, embedded tweets, and screenshots of the above, including text messages, omegle, snapchat, and others. This also includes any other sites that may be considered social network sites. Please read the [announcement.](http://www.reddit.com/1785g0)|\n\nWant to see /r/funny with these posts? [Click here!](/r/funny+facepalm+facebookwins+retorted+tumblr+twitter+texts+4chan+omegle+cleverbot+yahooanswers+screenshots+politicalhumor+demotivational+ecards+bestof+defaultgems+HIFW+MFW+reactiongifs+fffffffuuuuuuuuuuuu)\n\n------\n\n|||\n|-|-|\n|What do I do if I see a post that breaks the rules?|Click on the report button, and [send us a message](/message/compose?to=%23funny) with a link to the comments of the post.|\n|What should I do if I don't see my post in the new queue?|If your submission isn't showing up, please don't just delete it as that makes the filter hate you! Instead please [send us a message](/message/compose?to=%23funny) with a link to the post. We'll unban it and it should get better. Please allow 10 minutes for the post to appear before messaging moderators|\n\n-----\n\nLooking for something else? Visit our friends!\n\n+ [humor](/r/humor) for more in-depth stuff\n+ [tumblr](/r/tumblr) - for tumblr screenshots\n+ [NSFWFunny](/r/NSFWFunny)  \n+ [jokes](/r/jokes)\n+ [comics](/r/comics)\n+ [punny](/r/punny)\n+ [Very Punny](/r/verypunny)\n+ [lolcats](/r/lolcats)\n+ [Wheredidthesodago?](/r/wheredidthesodago)\n+ [lol](/r/lol)\n+ [Facepalm](/r/facepalm)\n+ [ReactionGifs](/r/reactiongifs)\n+ [ShittyAskScience](/r/shittyaskscience)\n+ [TrollingAnimals](/r/TrollingAnimals)\n+ [Rage Novels](/r/ragenovels/)\n+ [Demotivational](/r/Demotivational)\n+ [Screenshots](/r/screenshots)\n+ [Texts](/r/texts)\n+ [nononono](/r/nononono)\n+ [Disagreeable](/r/disagreeable)\n+ [gifs](/r/gifs)\n+ [\"how I think I am vs. how I actually am\"](/r/ExpectationVsReality/)\n+ [Unnecessary Censorship](/r/UnnecessaryCensorship)\n+ [Today I F*cked Up](/r/tifu)\n+ [Photoshop Battles](/r/photoshopbattles)\n+ [Clean Jokes](/r/cleanjokes)\n+ [Mean Jokes](/r/meanjokes)\n+ [FunniestVideos](/r/funniestvideos)\n+ [Funny Commercials](/r/funnycommercials)\n+ [Captions](/r/captioned)\n+ [Standup Shots](/r/standupshots)\n+ [Startled Cats](/r/startledcats)\n+ [Animals Being Jerks](/r/AnimalsBeingJerks)\n+ [Unexpected](/r/Unexpected)\n+ [MakeMeAGIF](/r/makemeagif)\n+ [accidentalcomedy](/r/accidentalcomedy)\n+ [bertstrips](/r/bertstrips) \n+ [misc](/r/misc/) for anything else\n+ [RedditDotCom](/r/redditdotcom) for anything else");
        funnyData.put("description_html", "&lt;!-- SC_OFF --&gt;&lt;div class=\"md\"&gt;&lt;p&gt;&lt;strong&gt;Welcome to r/Funny:&lt;/strong&gt;&lt;/p&gt;\n\n&lt;p&gt;You may only post if you are funny. &lt;/p&gt;\n\n&lt;hr/&gt;\n\n&lt;h1&gt;Rules&lt;/h1&gt;\n\n&lt;p&gt;&lt;em&gt;hover for details&lt;/em&gt;&lt;/p&gt;\n\n&lt;table&gt;&lt;thead&gt;\n&lt;tr&gt;\n&lt;th align=\"left\"&gt;&lt;/th&gt;\n&lt;th align=\"left\"&gt;&lt;/th&gt;\n&lt;/tr&gt;\n&lt;/thead&gt;&lt;tbody&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;1. No reactiongifs or HIFW posts&lt;/td&gt;\n&lt;td align=\"left\"&gt;How I Feel When posts belong in &lt;a href=\"/r/HIFW\"&gt;/r/HIFW&lt;/a&gt;. Reaction gifs belong in &lt;a href=\"/r/reactiongifs\"&gt;/r/reactiongifs&lt;/a&gt;.&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;2. No posts with their sole purpose being to communicate with another redditor.&lt;/td&gt;\n&lt;td align=\"left\"&gt;&lt;a href=\"http://www.reddit.com/lq3uv/\"&gt;Click for an Example&lt;/a&gt;. This includes asking for upvotes.&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;3. No Posts for the specific point of it being your reddit birthday.&lt;/td&gt;\n&lt;td align=\"left\"&gt;Cake day posts are not allowed.&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;4. Posts which result in harassment of any individual, subreddit, or other entity may be removed at the moderators&amp;#39; discretion.&lt;/td&gt;\n&lt;td align=\"left\"&gt;Posts with titles such as &amp;quot;I got banned from /r/___&amp;quot; or &amp;quot;This got removed from /r/___&amp;quot; are not allowed.&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;5. No Politics&lt;/td&gt;\n&lt;td align=\"left\"&gt;Anything involving politics or a political figure. Try &lt;a href=\"/r/politicalhumor\"&gt;/r/politicalhumor&lt;/a&gt; instead.&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;6. No Pictures of just text&lt;/td&gt;\n&lt;td align=\"left\"&gt;Make a self post instead. &lt;a href=\"http://i.imgur.com/2cPuY8C.jpg\"&gt;Example&lt;/a&gt;&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;7. No DAE posts&lt;/td&gt;\n&lt;td align=\"left\"&gt;Go to &lt;a href=\"/r/doesanybodyelse\"&gt;/r/doesanybodyelse&lt;/a&gt;&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;8. No Links to tumblr&lt;/td&gt;\n&lt;td align=\"left\"&gt;Direct links to images hosted on tumblr (ex. 24.media.tumblr.com/img.jpg) are allowed.&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;9. No URL shorteners&lt;/td&gt;\n&lt;td align=\"left\"&gt;No link shorteners (or HugeURL) in either post links or comments. They will be deleted regardless of intent.&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;10. No gore or porn (including sexually graphic images).&lt;/td&gt;\n&lt;td align=\"left\"&gt;Other NSFW content must be tagged as such&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;11. No personal information.&lt;/td&gt;\n&lt;td align=\"left\"&gt;This includes anything hosted on Facebook&amp;#39;s servers, as they can be traced to the original account holder.&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;12.  No memes, rage comics, demotivationals, eCards, or standupshots&lt;/td&gt;\n&lt;td align=\"left\"&gt;Memes belong in &lt;a href=\"/r/adviceanimals\"&gt;/r/adviceanimals&lt;/a&gt;, rage comics go to &lt;a href=\"/r/fffffffuuuuuuuuuuuu\"&gt;/r/fffffffuuuuuuuuuuuu&lt;/a&gt;, demotivationals go to &lt;a href=\"/r/Demotivational\"&gt;/r/Demotivational&lt;/a&gt;, submit eCards to &lt;a href=\"/r/ecards\"&gt;/r/ecards&lt;/a&gt;, and standupshots go to &lt;a href=\"/r/standupshots\"&gt;/r/standupshots&lt;/a&gt; &lt;a href=\"http://en.wikipedia.org/wiki/Image_macro#Formats\"&gt;Image Macros that aren&amp;#39;t memes are allowed&lt;/a&gt;&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;13. Do not rehost webcomics&lt;/td&gt;\n&lt;td align=\"left\"&gt;Rehosted webcomics will be removed. Please submit a link to the original comic&amp;#39;s site, and possibly a mirror in the comments. Unless you are the creator and it&amp;#39;s your preference to do so, do not &lt;a href=\"http://en.wikipedia.org/wiki/Inline_linking\"&gt;hotlink&lt;/a&gt; the comic if the page it&amp;#39;s located on can be linked to directly. Tumblr-exclusive comics are the exception, and may be rehosted, however if the artist&amp;#39;s name or watermark are removed, the post will be removed. &lt;a href=\"http://www.reddit.com/kqwwx/\"&gt;(*)&lt;/a&gt; &lt;a href=\"http://www.reddit.com/kwmk3\"&gt;(*)&lt;/a&gt;&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td align=\"left\"&gt;14. No SMS or Social Media Content (including Reddit)&lt;/td&gt;\n&lt;td align=\"left\"&gt;This includes direct linking to reddit threads, reddit comments, other subreddits, facebook profiles, twitter profiles, tweets, embedded tweets, and screenshots of the above, including text messages, omegle, snapchat, and others. This also includes any other sites that may be considered social network sites. Please read the &lt;a href=\"http://www.reddit.com/1785g0\"&gt;announcement.&lt;/a&gt;&lt;/td&gt;\n&lt;/tr&gt;\n&lt;/tbody&gt;&lt;/table&gt;\n\n&lt;p&gt;Want to see &lt;a href=\"/r/funny\"&gt;/r/funny&lt;/a&gt; with these posts? &lt;a href=\"/r/funny+facepalm+facebookwins+retorted+tumblr+twitter+texts+4chan+omegle+cleverbot+yahooanswers+screenshots+politicalhumor+demotivational+ecards+bestof+defaultgems+HIFW+MFW+reactiongifs+fffffffuuuuuuuuuuuu\"&gt;Click here!&lt;/a&gt;&lt;/p&gt;\n\n&lt;hr/&gt;\n\n&lt;table&gt;&lt;thead&gt;\n&lt;tr&gt;\n&lt;th&gt;&lt;/th&gt;\n&lt;th&gt;&lt;/th&gt;\n&lt;/tr&gt;\n&lt;/thead&gt;&lt;tbody&gt;\n&lt;tr&gt;\n&lt;td&gt;What do I do if I see a post that breaks the rules?&lt;/td&gt;\n&lt;td&gt;Click on the report button, and &lt;a href=\"/message/compose?to=%23funny\"&gt;send us a message&lt;/a&gt; with a link to the comments of the post.&lt;/td&gt;\n&lt;/tr&gt;\n&lt;tr&gt;\n&lt;td&gt;What should I do if I don&amp;#39;t see my post in the new queue?&lt;/td&gt;\n&lt;td&gt;If your submission isn&amp;#39;t showing up, please don&amp;#39;t just delete it as that makes the filter hate you! Instead please &lt;a href=\"/message/compose?to=%23funny\"&gt;send us a message&lt;/a&gt; with a link to the post. We&amp;#39;ll unban it and it should get better. Please allow 10 minutes for the post to appear before messaging moderators&lt;/td&gt;\n&lt;/tr&gt;\n&lt;/tbody&gt;&lt;/table&gt;\n\n&lt;hr/&gt;\n\n&lt;p&gt;Looking for something else? Visit our friends!&lt;/p&gt;\n\n&lt;ul&gt;\n&lt;li&gt;&lt;a href=\"/r/humor\"&gt;humor&lt;/a&gt; for more in-depth stuff&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/tumblr\"&gt;tumblr&lt;/a&gt; - for tumblr screenshots&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/NSFWFunny\"&gt;NSFWFunny&lt;/a&gt;&lt;br/&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/jokes\"&gt;jokes&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/comics\"&gt;comics&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/punny\"&gt;punny&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/verypunny\"&gt;Very Punny&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/lolcats\"&gt;lolcats&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/wheredidthesodago\"&gt;Wheredidthesodago?&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/lol\"&gt;lol&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/facepalm\"&gt;Facepalm&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/reactiongifs\"&gt;ReactionGifs&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/shittyaskscience\"&gt;ShittyAskScience&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/TrollingAnimals\"&gt;TrollingAnimals&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/ragenovels/\"&gt;Rage Novels&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/Demotivational\"&gt;Demotivational&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/screenshots\"&gt;Screenshots&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/texts\"&gt;Texts&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/nononono\"&gt;nononono&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/disagreeable\"&gt;Disagreeable&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/gifs\"&gt;gifs&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/ExpectationVsReality/\"&gt;&amp;quot;how I think I am vs. how I actually am&amp;quot;&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/UnnecessaryCensorship\"&gt;Unnecessary Censorship&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/tifu\"&gt;Today I F*cked Up&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/photoshopbattles\"&gt;Photoshop Battles&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/cleanjokes\"&gt;Clean Jokes&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/meanjokes\"&gt;Mean Jokes&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/funniestvideos\"&gt;FunniestVideos&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/funnycommercials\"&gt;Funny Commercials&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/captioned\"&gt;Captions&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/standupshots\"&gt;Standup Shots&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/startledcats\"&gt;Startled Cats&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/AnimalsBeingJerks\"&gt;Animals Being Jerks&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/Unexpected\"&gt;Unexpected&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/makemeagif\"&gt;MakeMeAGIF&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/accidentalcomedy\"&gt;accidentalcomedy&lt;/a&gt;&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/bertstrips\"&gt;bertstrips&lt;/a&gt; &lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/misc/\"&gt;misc&lt;/a&gt; for anything else&lt;/li&gt;\n&lt;li&gt;&lt;a href=\"/r/redditdotcom\"&gt;RedditDotCom&lt;/a&gt; for anything else&lt;/li&gt;\n&lt;/ul&gt;\n&lt;/div&gt;&lt;!-- SC_ON --&gt;");
        funnyData.put("display_name", "funny");
        funnyData.put("header_img", "http://e.thumbs.redditmedia.com/g2Xn0gAOiibrx1j4.png");
        funnyData.put("header_size", jsonArrayOf(160, 64));
        funnyData.put("header_title", "Logo by corvuskorax");
        funnyData.put("id", "2qh33");
        funnyData.put("name", "t5_2qh33");
        funnyData.put("over18", false);
        funnyData.put("public_description", "");
        funnyData.put("public_traffic", true);
        funnyData.put("submission_type", "any");
        funnyData.put("submit_link_label", null);
        funnyData.put("submit_text", "");
        funnyData.put("submit_text_html", null);
        funnyData.put("submit_text_label", null);
        funnyData.put("subreddit_type", "public");
        funnyData.put("subscribers", 5619102);
        funnyData.put("title", "funny");
        funnyData.put("url", "/r/funny/");
        funnyData.put("user_is_banned", null);
        funnyData.put("user_is_contributor", null);
        funnyData.put("user_is_moderator", null);
        funnyData.put("user_is_subscriber", null);

        JSONObject funnyKind = new JSONObject();
        funnyKind.put("kind", "t5");

        JSONObject funnyListing = new JSONObject();
        funnyListing.put("data", funnyData);
        funnyListing.put("kind", funnyKind);

        JSONObject data = new JSONObject();
        data.put("after", "t5_2s5oq");
        data.put("before", null);
        data.put("children", jsonArrayOf(funnyListing));
        data.put("modhash", "");

        JSONObject root = new JSONObject();

        root.put("data", data);
        root.put("kind", "Listing");

        return root;
    }
}
