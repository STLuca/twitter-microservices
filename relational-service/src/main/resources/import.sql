CREATE OR REPLACE VIEW UserView AS
    SELECT u.ID as id,
            u.USERNAME as username,
            (SELECT count(*) FROM Tweets t where t.USER_ID = u.id) as tweetCount,
            (SELECT count(*) FROM Follows f where f.FOLLOWER_ID = u.id) as followingCount,
            (SELECT count(*) FROM Follows f where f.FOLLOWEE_ID = u.id) as followerCount
    FROM Users u;

CREATE OR REPLACE VIEW TweetView AS
    SELECT  t.ID as id,
            t.MESSAGE as message,
            u.ID as userID,
            u.USERNAME as username,
            (SELECT count(*) FROM Likes l WHERE l.TWEET_ID = t.ID) as likeCount,
            (SELECT count(*) FROM Replies r WHERE r.PARENT_ID = t.ID) as replyCount,
            (SELECT r.PARENT_ID FROM replies r WHERE r.CHILD_ID = t.ID LIMIT 1) as replyTo
    FROM Tweets t
    INNER JOIN Users u ON t.USER_ID = u.ID;

DROP FUNCTION IF EXISTS getUserView(BIGINT);
CREATE FUNCTION getUserView(userId BIGINT)
    RETURNS TABLE (
		id BIGINT,
		username VARCHAR,
		tweetcount BIGINT,
		followingcount BIGINT,
		followercount BIGINT,
		following BOOLEAN,
		follower BOOLEAN
	)
    AS
    '
    BEGIN
        RETURN QUERY
		SELECT u.*,
			EXISTS (SELECT * FROM Follows f WHERE f.followee_id = userId AND f.follower_id = u.id) as following,
			EXISTS (SELECT * FROM Follows f WHERE f.followee_id = u.id AND f.follower_id = userId) as follower
		FROM UserView u;
    END;
    '
LANGUAGE 'plpgsql';

DROP FUNCTION IF EXISTS getTweetView(BIGINT);
CREATE FUNCTION getTweetView(inputUserID BIGINT)
    RETURNS TABLE (
		id BIGINT,
		message VARCHAR,
		userid BIGINT,
		username VARCHAR,
		likecount BIGINT,
		replycount BIGINT,
		replyTo BIGINT,
		liked BOOLEAN
	)
    AS '
    BEGIN
        RETURN QUERY
		SELECT tweet.*,
			EXISTS (SELECT * FROM Likes l WHERE l.user_id = inputUserID AND l.tweet_id = tweet.id) as liked
		FROM TweetView tweet;
    END; '
LANGUAGE 'plpgsql';

DROP FUNCTION IF EXISTS getFollowingUserIDs(BIGINT);
CREATE FUNCTION getFollowingUserIDs(user_id BIGINT)
    RETURNS TABLE (
		id BIGINT
	)
    AS '
    BEGIN
        RETURN QUERY
		SELECT f.followee_id
        FROM follows f
        WHERE f.follower_id = user_id;
    END; '
LANGUAGE 'plpgsql';

DROP FUNCTION IF EXISTS getFollowedUserIDs(BIGINT);
CREATE FUNCTION getFollowedUserIDs(user_id BIGINT)
    RETURNS TABLE (
		id BIGINT
	)
    AS '
    BEGIN
        RETURN QUERY
		SELECT f.follower_id
        FROM follows f
        WHERE f.followee_id = user_id;
    END; '
LANGUAGE 'plpgsql';

DROP FUNCTION IF EXISTS getFollowingUsers(BIGINT, BIGINT);
CREATE FUNCTION getFollowingUsers(user_id BIGINT, queryingUser BIGINT)
    RETURNS TABLE (
		id BIGINT,
        username VARCHAR,
        tweetcount BIGINT,
        followingcount BIGINT,
        followercount BIGINT,
        following BOOLEAN,
        follower BOOLEAN
	)
    AS '
    BEGIN
        RETURN QUERY
		SELECT u.*
        FROM getUserView(queryingUser) u
        WHERE u.id in (SELECT * FROM getFollowingUserIDs(user_id));
    END; '
LANGUAGE 'plpgsql';

DROP FUNCTION IF EXISTS getFollowedUsers(BIGINT, BIGINT);
CREATE FUNCTION getFollowedUsers(user_id BIGINT, queryingUser BIGINT)
    RETURNS TABLE (
		id BIGINT,
        username VARCHAR,
        tweetcount BIGINT,
        followingcount BIGINT,
        followercount BIGINT,
        following BOOLEAN,
        follower BOOLEAN
	)
    AS '
    BEGIN
        RETURN QUERY
		SELECT u.*
        FROM getUserView(queryingUser) u
        WHERE u.id in (SELECT * FROM getFollowedUserIDs(user_id));
    END; '
LANGUAGE 'plpgsql';

DROP FUNCTION IF EXISTS getFeed(BIGINT, BIGINT);
CREATE FUNCTION getFeed(user_id BIGINT, queryingUser BIGINT)
    RETURNS TABLE (
		id BIGINT,
        message VARCHAR,
        userid BIGINT,
        username VARCHAR,
        likecount BIGINT,
        replycount BIGINT,
		replyTo BIGINT,
        liked BOOLEAN
	)
    AS '
    BEGIN
        RETURN QUERY
		SELECT tweets.*
        FROM getTweetView(queryingUser) tweets
        WHERE tweets.userid IN (SELECT * FROM getFollowingUserIDs(users_id));
    END; '
LANGUAGE 'plpgsql';

DROP FUNCTION IF EXISTS getTweetLikesUserIDs(BIGINT);
CREATE FUNCTION getTweetLikesUserIDs(tweet_id BIGINT)
    RETURNS TABLE (
		id BIGINT
	)
    AS '
    BEGIN
        RETURN QUERY
		SELECT l.user_id
        FROM likes l
        WHERE l.tweet_id = tweet_id;
    END; '
LANGUAGE 'plpgsql';

DROP FUNCTION IF EXISTS getTweetLikes(BIGINT, BIGINT);
CREATE FUNCTION getTweetLikes(tweet_id BIGINT, queryingUser BIGINT)
    RETURNS TABLE (
		id BIGINT,
        username VARCHAR,
        tweetcount BIGINT,
        followingcount BIGINT,
        followercount BIGINT,
        following BOOLEAN,
        follower BOOLEAN
	)
    AS '
    BEGIN
        RETURN QUERY
		SELECT u.*
        FROM getUserView(queryingUser) u
        WHERE u.id IN (SELECT * FROM getTweetLikesUserIDs(tweet_id));
    END; '
LANGUAGE 'plpgsql';

DROP FUNCTION IF EXISTS getUsersTweets(BIGINT, BIGINT);
CREATE FUNCTION getUsersTweets(queryingUser BIGINT, user_id BIGINT)
    RETURNS TABLE (
		id BIGINT,
        message VARCHAR,
        userid BIGINT,
        username VARCHAR,
        likecount BIGINT,
        replycount BIGINT,
        replyTo BIGINT,
        liked BOOLEAN
	)
    AS '
    BEGIN
        RETURN QUERY
		SELECT *
        FROM getTweetView(queryingUser) t
        WHERE t.user_id = user_id;
    END; '
LANGUAGE 'plpgsql';
