/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooledmining;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mahmed27
 */
public class PooledMining {
    
    private static final int DEFAULT_TIMEOUT = 0; // ms
    private final static char[] BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final String DEFAULT_URL = "http://176.9.147.17:45690/";
    private static final String DEFAULT_AUTH = "mahmed27@uncc.edu:x";
    private static final long DEFAULT_SCAN_TIME = 5000;
    private static final long DEFAULT_RETRY_PAUSE = 30000;
    public static byte[] data;
    public static String userId; //= "03443f96-e7c8-4fb5-8359-be4bb0a501c1";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        if(args.length < 3) return;
//        String pass = (args.length == 3) ? "" : args[3].trim();
        while(true) {
            try {
                
//                if(args[0].trim().contains("minergate")) {
//                    MiningUtility.connectToPoolMinergate(args[0].trim(), Integer.parseInt(args[1].trim()), args[2].trim(), pass);
//                } else {
//                    MiningUtility.connectToStratumMiningServer(args[0].trim(), Integer.parseInt(args[1].trim()), args[2].trim(), pass);
//                }
                
//                connectToPool(MiningUtility.minergatePoolServerAddress, MiningUtility.minergateServerport);
//                MiningUtility.connectToPoolMinergate(MiningUtility.minergatePoolServerAddress, MiningUtility.minergateServerport
//                        , MiningUtility.minergateUsername, MiningUtility.passwordEmpty);

                MiningUtility.connectToStratumMiningServer(MiningUtility.slushpoolServerAddressBitCoin
                        , MiningUtility.slushpoolServerPortBitcoin, MiningUtility.sluspoolUsername, MiningUtility.passwordX); // nonce exact 8

//                MiningUtility.connectToStratumMiningServer(MiningUtility.kanoPoolServerAddress
//                        , MiningUtility.kanoPoolServerPort, MiningUtility.kanoPoolUsername, MiningUtility.passwordX);

         
//                MiningUtility.connectToStratumMiningServer(MiningUtility.f2poolServerAddressBitcoin, MiningUtility.f2poolServerportBitcoin
//                        , MiningUtility.f2poolusername, MiningUtility.passwordX);

//                MiningUtility.connectToStratumMiningServer(MiningUtility.antPoolServerAddressBitcoin
//                        , MiningUtility.antPoolServerportBitcoin, MiningUtility.antPoolUsername, MiningUtility.passwordX);

//                MiningUtility.connectToStratumMiningServer(MiningUtility.giveMeCoinsPoolServerAddress
//                        , MiningUtility.giveMeCoinsServerport, MiningUtility.giveMeCoinsUsername, MiningUtility.passwordX);

//                MiningUtility.connectToStratumMiningServer(MiningUtility.eligiusPoolServerAddress
//                        , MiningUtility.eligiusPoolServerport, MiningUtility.eligiusPoolUsername, MiningUtility.passwordEmpty);

//                MiningUtility.connectToStratumMiningServer(MiningUtility.mmPoolServerAddress
//                        , MiningUtility.mmPoolServerport, MiningUtility.mmPoolUsername, MiningUtility.passwordEmpty);
//
//                MiningUtility.connectToStratumMiningServer(MiningUtility.multiPoolServerAddress
//                        , MiningUtility.multiPoolServerport, MiningUtility.multiPoolUsername, MiningUtility.passwordEmpty);
////
//                MiningUtility.connectToStratumMiningServer(MiningUtility.ozCoinPoolServerAddress
//                        , MiningUtility.ozCoinPoolServerport, MiningUtility.ozCoinPoolUsername, MiningUtility.passwordX);
////
//                MiningUtility.connectToStratumMiningServer(MiningUtility.bixinPoolServerAddress
//                        , MiningUtility.bixinPoolServerport, MiningUtility.bixinPoolUsername, MiningUtility.passwordEmpty);
            } catch (Exception ex) {
//                Logger.getLogger(PooledMining.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    static void connectToPool(String SERVER, int PORT) { 

        String message1 = "{\"id\":1,\"method\":\"subscribe\",\"params\":[]}";
        String message11 = "{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"login\",\"params\":{\"agent\":\"MinerGateMac/6.9\",\"login\":\"mahmed27@uncc.edu\",\"pass\":\"\"}}";
        
        boolean result = false;
        DataInputStream is;
        DataOutputStream os;

        try{
            InetAddress address = InetAddress.getByName(SERVER);
            System.out.println("Atempting to connect to " + address.toString() + " on port " + PORT + ".");

            // connect 
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(SERVER, PORT));
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            PrintWriter pw = new PrintWriter(os);
            pw.println(message11);                                       //connect
            pw.flush();
            System.out.println(message11);

            //read response
            StringBuilder sb = new StringBuilder();
            String line = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
    //        JsonObject json = new JSONObject(in.readLine());
            while ((line = in.readLine()) != null) {
//                sb.append(line);
                System.out.println("Response:" +line);
                String jobId = PooledMining.parseJson(line);
                if(jobId != null) {
                    PooledMining.prepareSubmissionData(jobId, pw);
                }
            }
//            System.out.println("json response: " + sb.toString()/*json.toString()*/);


            is.close();
            os.close();
            socket.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Not able to connect to pool");
            System.exit(-2);
        } 
    }
    
    public static String parseJson(String jsonData) {
        String jobId = null;
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(jsonData);
        System.out.println(obj.get("params"));
        if(obj.has("result") && !obj.get("result").isJsonNull() && obj.get("result").getAsJsonObject().has("job") 
                && !obj.get("result").getAsJsonObject().get("job").isJsonNull()) {
            userId = obj.get("result").getAsJsonObject().get("id").getAsString();
            jobId = obj.get("result").getAsJsonObject().get("job").getAsJsonObject().get("job_id").getAsString();
            System.out.println("JobId from result :" + jobId);
            System.out.println("UserId from result :" + userId);
        } else if(obj.has("method") && !obj.get("method").isJsonNull() && obj.get("method").getAsString().contains("job") 
                && obj.has("params") && !obj.get("params").isJsonNull()) {
            jobId = obj.get("params").getAsJsonObject().get("job_id").getAsString();
//            System.out.println("JobId from params : " + jobId);
//            System.out.println("UserId from result :" + userId);
        }
        return jobId;
    }
    
    public static String prepareSubmissionData(String jobId, PrintWriter writer) {
        String part1 = "\"id\":\"";
        String part2 = "\",\"jsonrpc\":\"2.0\",\"method\":\"submit\",\"params\":{" + part1;
        String part3 = "\",\"job_id\":\"";
        String part4 = "\",\"nonce\":\"";
        String part5 = "\",\"result\":\"";
        String endingPart = "\"}}";
        String nonce = "0606000";
        String result = "f57ea0cd4bdbb9df9dad5df7545ac5c9e6ac94196b47c5d134d17b91ae053c1";
        String finalJson = "";
        Random rand = new Random();
        for(int i = 1; i < 15000; i++) {
            finalJson = "{" + part1 + i + part2 + userId + part3 + jobId + part4 + nonce + rand.nextInt(10) + part5 + result + rand.nextInt(10) + endingPart;
//            System.out.println(finalJson);
            writer.println(finalJson);
            writer.flush();
        }
        return null;
    }
    
    public static HttpURLConnection getJsonRpcConnection(URL url, String request, String auth) throws IOException {
            return getJsonRpcConnection((HttpURLConnection) url.openConnection(), request, auth);
    }
    
    public static void getWork(HttpURLConnection conn, URL mainUrl, String auth) throws IOException {
        String request = "{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"login\",\"params\":{\"agent\":\"MinerGateMac/6.9\",\"login\":\"mahmed27@uncc.edu\",\"pass\":\"\"}}";

        HttpURLConnection conn1 = getJsonRpcConnection(conn, request, auth);
        int response = conn1.getResponseCode();
        System.out.println("Response: " + response);
        if (response == 401 || response == 403) {
                System.out.println("Response: " + response);
                throw new IllegalArgumentException("Access denied");
        }
        String content = getConnectionContent(conn1);
//        System.out.println(content);
    }
    
    public static HttpURLConnection getJsonRpcConnection(HttpURLConnection conn, String request, String auth) throws IOException {
        if (conn.getConnectTimeout() == 0) {
                conn.setConnectTimeout(DEFAULT_TIMEOUT);
        }
        if (conn.getReadTimeout() == 0) {
                conn.setReadTimeout(DEFAULT_TIMEOUT);
        }
    	conn.setRequestMethod("POST");
    	if (auth != null) {
    		//conn.setRequestProperty("Authorization", "Basic " + stringToBase64(auth));
        }
    	conn.setRequestProperty("Content-Type", "application/json");
    	conn.setRequestProperty("Content-Length", Integer.toString(request.getBytes().length));
    	conn.setRequestProperty("X-Mining-Extensions", "midstate");
    	conn.setAllowUserInteraction(false);
    	conn.setUseCaches(false);
	conn.setDoOutput(true);
		
	DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
	wr.writeBytes(request);
	wr.close();
        return conn;
    }
    
    public static String getConnectionContent(HttpURLConnection conn) throws IOException {
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        byte[] buffer = new byte[4096];
        while ((len = is.read(buffer)) != -1) {
        	bos.write(buffer, 0, len);
        }
    	String content = bos.toString();
        is.close();
        return content;
    }
    
    public static String stringToBase64(String str) {
        byte[] buf = str.getBytes();
        int size = buf.length;
        char[] ar = new char[((size + 2) / 3) * 4];
        int a = 0;
        int i = 0;
        while (i < size) {
            byte b0 = buf[i++];
            byte b1 = (i < size) ? buf[i++] : 0;
            byte b2 = (i < size) ? buf[i++] : 0;
            ar[a++] = BASE64_ALPHABET[(b0 >> 2) & 0x3f];
            ar[a++] = BASE64_ALPHABET[((b0 << 4) | ((b1 & 0xFF) >> 4)) & 0x3f];
            ar[a++] = BASE64_ALPHABET[((b1 << 2) | ((b2 & 0xFF) >> 6)) & 0x3f];
            ar[a++] = BASE64_ALPHABET[b2 & 0x3f];
        }
        switch (size % 3) {
        case 1:
                ar[--a] = '=';
        case 2:
                ar[--a] = '=';
        }
        return new String(ar);
    }
    
    public static boolean submit(int nonce, URL url, String auth) throws IOException {
        byte[] d = (byte[]) data.clone();
        d[79] = (byte) (nonce >>  0);
        d[78] = (byte) (nonce >>  8);
        d[77] = (byte) (nonce >> 16);
        d[76] = (byte) (nonce >> 24);
        String sData = byteArrayToHexString(d);
        String request = "{\"method\": \"getwork\", \"params\": [ \"" + sData + "\" ], \"id\":1}";

        HttpURLConnection conn = getJsonRpcConnection(url, request, auth);
        String content = getConnectionContent(conn);

//        Matcher m = resultPattern.matcher(content);
//        if (m.find() && m.group(1).equals("true"))
//                return true;
            return false;
    }
    
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(80);
        for (int i = 0; i < b.length; i++) {
                sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
	
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    
    
}
