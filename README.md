# News-recommendation
2019-1 CAUCSE Capstone Project Team 7


# APIs

## Article

* /article/get
 	* Type
	  * GET
  * when you call this API?
    * need to renew User's recommended articles List
  
  * what it does?
    * Get recent 10 articles in "ArticleHistory" Table
    
  * params
    * String uid    // means user's id
  
  * example
    * http://`SERVER-IP`:`PORT`/article/get?uid=usertest00
    
* /article/read
	* Type
	 	* POST
	* when you call this API?
    * User's article click Action
    
  * what it does?
    * Make "read" field of "ArticleHistory" Table to 1
  
  * params
    * String uid    // means user's id
    * String aid    // means article's id
  
  * example
    * http://`SERVER-IP`:`PORT`/article/read
    * payload
      * {"aid":19,"uid":"usertest00"}


* /article/feedback
  * Type
	  * POST
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
    * http://`SERVER-IP`:`PORT`/article/feedback
    * payload
      * {"aid":19,"uid":"usertest00", "feedback":true}


    
 ## User
 
 * /user/initUser
 	 * Type
	 	 * POST
		 
 	 * when you call this API?
	   * initialize user
		 
	 * what it does?
	   * make user id and set to Table
		 
	 * example
	 	 * http://`SERVER-IP`:`PORT`/user/initUser
 
 
 * /user/getInitialKeywords
	 * Type
	 	 * GET
 	 
	 * when you call this API?
	   * for users initial keyword setting
		 
	 * what it does?
	   * it gives you some keywords
		 
	 * params
	   * String uid				// means user's id
		 
	 * example
	 	 * http://`SERVER-IP`:`PORT`/user/getInitialKeywords?uid:usertest00
 
 
 * /user/initKeyword
	 * Type
	 	 * POST
		 
   * when you call this API?
     * user finished to select some keywords.
   
   * what it does?
     * registers user's keyword to "User" Table
    
   * params
	 	 * 
