package com.ketsoft.liteerp.constants;

public class PathAuthConstant {

	// Endpoints que não requerem autenticação para serem acessados
	public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
    		"/",
    	    "/user/login",
    	    "/user/create",
    	    "/infos",
    	    "/index.html"
    };
	
	 // Endpoints que requerem autenticação para serem acessados
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/user/test"
    };
}
