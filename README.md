# News-recommendation
2019-1 CAUCSE Capstone Project Team 7


# APIs

## Article

* /article/get
  * when you call this API?
    * need to renew User's recommended articles List
  
  * what it does?
    * Get recent 10 articles in "ArticleHistory" Table
    
  * params
    * String uid    // means user's id
  
  * example
    * http://`YOUR-IP`:`YOUR-PORT`/article/get?uid=usertest00
    
* /article/read
  * when you call this API?
    * User's article click Action
    
  * what it does?
    * Make "read" field of "ArticleHistory" Table to 1
  
  * params
    * String uid    // means user's id
    * String aid    // means article's id
  
  * example
    * http://`YOUR-IP`:`YOUR-PORT`/article/read
    * payload
      * {"aid":19,"uid":"usertest00"}


* /article/feedback
  * when you call this API?
    * User's article feedback button click Action
    
  * what it does?
    * Make "feedback" field of "ArticleHistory" Table to payload's value of "feedback"
  
  * params
    * String uid        // means user's id
    * String aid        // means article's id
    * Boolean feedback  // means user's feedback
      * true              // user like this article
      * false             // user don't care this article
      * null              // no user feedback action for this article
  
  * example
    * http://`YOUR-IP`:`YOUR-PORT`/article/feedback
    * payload
      * {"aid":19,"uid":"usertest00", "feedback":true}


    
 ## User
 
 * /user
