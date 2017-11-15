package weibo4j.examples.oauth2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import weibo4j.Oauth;
import weibo4j.Timeline;
import weibo4j.http.AccessToken;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;
import weibo4j.org.json.JSONString;
import weibo4j.util.BareBonesBrowserLaunch;

public class OAuth4Code {
	public static void main(String [] args) throws WeiboException, IOException{
//		loginOAuth();
		// {"access_token":"2.00SlRLkDXGmTxCcc0168de910P2FtR","remind_in":"157679999","expires_in":157679999,"uid":"3430799840","isRealName":"true"}
		Timeline timeline = new Timeline("2.00SlRLkDXGmTxCcc0168de910P2FtR");
		StatusWapper statusWapper = timeline.getFriendsTimeline();
		System.out.println(statusWapper);
	}

	public static AccessToken getAccessTokenByCode(String code) throws WeiboException {
		Oauth oauth = new Oauth();
		AccessToken accessToken = oauth.getAccessTokenByCode(code);
		return accessToken;
	}

	public static void loginOAuth() throws WeiboException, IOException {
		Oauth oauth = new Oauth();
		BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
		System.out.println(oauth.authorize("code"));
		System.out.print("Hit enter when it's done.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		Log.logInfo("code: " + code);
		try{
			System.out.println(oauth.getAccessTokenByCode(code));
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
				Log.logInfo("Unable to get the access token.");
			}else{
				e.printStackTrace();
			}
		}
	}
}
