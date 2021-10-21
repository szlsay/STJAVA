package com.tensquare.encrypt.rsa;

/**
 * rsa加解密用的公钥和私钥
 * @author Administrator
 *
 */
public class RsaKeys {

	//生成秘钥对的方法可以参考这篇帖子
	//https://www.cnblogs.com/yucy/p/8962823.html

	//服务器公钥
	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyR8kw1lNYO9/XY1yrhxi\n" +
			"Ryy2OdUiM4y5qTfGTPseWxn12Y7KCS4R+scWrPwLcuqSGa1QJguggQEI7+nal5zo\n" +
			"o+NDCwL1UxRP9411SguTfI9v+8fsA9ydUZvKLh4xUN9uXJcRwurKmNLoIqG1vLu1\n" +
			"dshuGnHW97aHw5SRMgxSjjtv5hoECKyHgCVfEaMa+RlxG7G4iFOgsNq3EuuMYXeQ\n" +
			"3OzX76vhnk5/TxoQS0zWHo0sNOiKqOBdJrNd7oojrmgDiAFSTolU6gYqHNAjr7xb\n" +
			"3icZOFcQH2HoITkX9Q+wVoz+Rz2yUOJiRoJwa4zzAJ7itbintzBL8X+uT7PIug/t\n" +
			"pwIDAQAB";


	//服务器私钥(经过pkcs8格式处理)
	private static final String serverPrvKeyPkcs8 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDJHyTDWU1g739d\n" +
			"jXKuHGJHLLY51SIzjLmpN8ZM+x5bGfXZjsoJLhH6xxas/Aty6pIZrVAmC6CBAQjv\n" +
			"6dqXnOij40MLAvVTFE/3jXVKC5N8j2/7x+wD3J1Rm8ouHjFQ325clxHC6sqY0ugi\n" +
			"obW8u7V2yG4acdb3tofDlJEyDFKOO2/mGgQIrIeAJV8Roxr5GXEbsbiIU6Cw2rcS\n" +
			"64xhd5Dc7Nfvq+GeTn9PGhBLTNYejSw06Iqo4F0ms13uiiOuaAOIAVJOiVTqBioc\n" +
			"0COvvFveJxk4VxAfYeghORf1D7BWjP5HPbJQ4mJGgnBrjPMAnuK1uKe3MEvxf65P\n" +
			"s8i6D+2nAgMBAAECggEAD4c8C2kltEpJhyuYoyDF5cDqAOUX0usWmp4XzXHGEjqa\n" +
			"5MVFjBfmg3pDoe+D4seBqEHdYlKbfdpamktv+SzAOYcecgc0ccmrsu1VFPZ32LIq\n" +
			"b0IsUtABxX9FyPHD1P6rQrLXSDlgkxQVoDbkFUJY7GblOV14v390VAFwhHC3HwwM\n" +
			"t5ACHsdvdovNp8ke+rqd+vTooY6BQGtf+PY01nKvlh8yL6y0LmG/2mSoNgw6+NHG\n" +
			"qIg2PaiPPdiPsfWAfJhggsk/U62KavbrVXVZqa/VHtwkXvCzI/BaXFb27ZHKfDr8\n" +
			"GYFZXtBPqe1v02mrTuqt7S7ncIYEnR42rak8Fs7uIQKBgQDn+i9xST6ri+QPsFwd\n" +
			"jF7lGiJZNqoPW/3EHu1Rpxt1zD1honcExmF+tfBC02Mjv3O6c96qyydNwsny9zij\n" +
			"A0/VwQSdIzmcCS+pw4olETQ0x1XfdCh4N/Dg+8/JjE8fmd79+qoSBTtWzEEjSNgw\n" +
			"HMGXOddjY9qDDMlP/7PFeBatqwKBgQDd8vZtkqBWsYJ2qS5c86E27jBHUH3qbezq\n" +
			"o4qVj0dcY+v0o3kRtWzJv5aL9y6nY+sgsEyETK7lYK8OjqG4arJUePwyMB/bQYD7\n" +
			"+odnsuVNzulYxo5ph1c12+IovbY93WO20D9LSo466+6lH8wZReoAaThBUaRzIa9/\n" +
			"gf1+55Ar9QKBgFbsQ0yywl3rXDiYv9t+lqxIH6jIN+7rbGpJoGlhMYPxVOq5wtky\n" +
			"MKQXLoELznE9svKhqkTbPM4BeESnaDyE6CxTWBQ9d7/FHWwCTkhXavV/3+iMEQID\n" +
			"DCpxkMmX3qobsRgADiwUc8ixXpMHH6Lfk5bD4LZoWx+R8fkc+lK7DdxjAoGBAIjq\n" +
			"8iRkOK9ib/YdpfFHhiNpyrSjrhmpWGbjHoHm0oHnTk8rbWuEPSk0HH4ZrVkr5YfT\n" +
			"2lAfMiFkhHl5ecBXac18mS+oym4quZQ4V8Gb9aMLSp4uVvo4uI8MJzc02TDO202S\n" +
			"qAkdZCJrG1wYAoNwwssgHckFwWoY1LfXC/d2iFTlAoGAc7gZsIHsCfwxc1lpUOzf\n" +
			"MAzHzBhugRIeKEXPRnFupfSOjnPWP4ImOgiA156vpr4Nh9BhQzaBDpvI0XY7ZT8A\n" +
			"2GrSAGdUKeEVrK3/KRpD1q3AZWwqiITQdgCKSJueJJ1h3lJpALSBFa8crQwsFtH5\n" +
			"zxGUucPXK1XLAMeZcIHCYp4=";
	public static String getServerPubKey() {
		return serverPubKey;
	}

	public static String getServerPrvKeyPkcs8() {
		return serverPrvKeyPkcs8;
	}
	
}
