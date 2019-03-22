<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TweetCool</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <c:choose>
        <c:when test="${tweets != null}">
            <form action="/TweetCool/tweets" method="POST">
                Limit:
                <select id="limit" name="limit">
                    <option value="10" selected>10</option>
                    <option value="20">20</option>
                    <option value="50">50</option>
                </select>
                <br>
                Offset:
                <select id="offset" name="offset">
                    <option value="0" selected>0</option>
                    <option value="2">2</option>
                    <option value="4">4</option>
                    <option value="8">8</option>
                </select>
                <br>
                Poster:
                <input type="text" name="poster">
                <br>
                Date:
                <input type="datetime-local" name="date" />
                <br>
                <p align="center"><input type="submit" value="Tweet filtering"></p>
            </form>
            <c:forEach var="tweet" items="${tweets.getTweets()}">
                <c:out value="${tweet.getUser()}"/> : <c:out value="${tweet.getPost()}"/>
                <br>
                <c:out value="${tweet.getDateOfPosting()}"/>
                <br>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>There are no tweets to show</p>
        </c:otherwise>
    </c:choose>
    <p align="center"><a href="index.html">Home page</a></p>
</body>
</html>
