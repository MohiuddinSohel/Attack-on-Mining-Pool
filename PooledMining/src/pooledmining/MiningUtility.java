/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooledmining;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import static pooledmining.PooledMining.userId;


/**
 *
 * @author mahmed27
 */
public class MiningUtility {
    
    public static final String sluspoolUsername = "bf-canada.worker1234";//"MohiuddinSohel.Sohel33";
    public static final String sluspoolpassword = "";
    public static final String passwordEmpty = "";
    public static final String passwordX = "x";
    
    public static final String slushpoolServerAddressBitCoin = "stratum.slushpool.com";
    public static final String slushpoolServerAddressBitCoinUSAEastCost = "us-east.stratum.slushpool.com";
    public static final String slushpoolServerAddressBitCoinEurope = "eu.stratum.slushpool.com";
    public static final String slushpoolServerAddressBitCoinChinaMainland = "cn.stratum.slushpool.com";
    public static final String slushpoolServerAddressBitCoinAsia = "sg.stratum.slushpool.com";
    public static final int slushpoolServerPortBitcoin = 3333;
    public static final int slushpoolServerPortBitcoinChinaMainland = 443;
    
    
    public static final String slushpoolServerAddressZcash = "zec.slushpool.com";
    public static final String slushpoolServerAddressZcashUSAEastCost = "us-east.zec.slushpool.com";
    public static final String slushpoolServerAddressZcashEurope = "eu.zec.slushpool.com";
    public static final int slushpoolServerPortZcash = 4444;
    
    
    public static final String kanoPoolUsername = "mohiuddinsohel.worker";
    public static final String kanoPoolServerAddress = "stratum.kano.is";
    public static final int kanoPoolServerPort = 3333;
    public static final int kanoPoolServerPort80 = 80;
    public static final int kanoPoolServerPort81 = 81;
    public static final int kanoPoolServerPort443 = 443;
    public static final int kanoPoolServerPort8080 = 8080;
    public static final int kanoPoolServerPort27181 = 27181;
    
    public static final String f2poolServerAddressBitcoin = "stratum.f2pool.com";
    public static final int f2poolServerportBitcoin = 3333;
    public static final String f2poolServerAddressLitecoin = "stratum.f2pool.com";
    public static final int f2poolServerportLitecoin = 8888;
    public static final String f2poolServerAddressZeccoin = "zec.f2pool.com";
    public static final int f2poolServerportZeccoin = 3357;
    public static final String f2poolServerAddressEtherium = "eth.f2pool.com";
    public static final int f2poolServerportEtherium = 8118;
    public static final String f2poolusername = "mohiuddinsohel1.001";
    
    public static final String antPoolServerAddressBitcoin = "stratum.antpool.com";
    public static final int antPoolServerportBitcoin = 3333;
    public static final String antPoolServerAddressLitecoin = "stratum-ltc.antpool.com";
    public static final int antPoolServerportLitecoin = 8888;
    public static final String antPoolServerAddressEthereum = "stratum-eth.antpool.com";
    public static final int antPoolServerportEthereum = 8008;
    public static final String antPoolServerAddressZcash = "stratum-zec.antpool.com";
    public static final int antPoolServerportZcash = 8899;
    public static final String antPoolUsername = "mohiuddinsohel.sohel";
    
    public static final String giveMeCoinsPoolServerAddress = "ltc.give-me-coins.com";
    public static final int giveMeCoinsServerport = 3333;
    public static final String giveMeCoinsUsername = "MohiuddinSohel.1";
    
    public static final String minergatePoolServerAddress = "aeon.pool.minergate.com";//"xmr.pool.minergate.com";
    public static final int minergateServerport = 45690;//45560;
    public static final String minergateUsername = "mahmed27@uncc.edu";//"0xb2930b35844a230f00e51431acae96fe543a0347";//mahmed27@uncc.edu";
    
    public static final String multiPoolServerAddress = "us.multipool.us";
    public static final int multiPoolServerport = 3332;
    public static final String multiPoolUsername = "mohiuddinsohel.1";
    
    public static final String ozCoinPoolServerAddress = "spare.ozco.in";
    public static final int ozCoinPoolServerport = 9333;
    public static final String ozCoinPoolUsername = "mohiuddinsohel.1234";
    
    public static final String bixinPoolServerAddress = "stratum.bixin.com";
    public static final int bixinPoolServerport = 3333;
    public static final String bixinPoolUsername = "12jJfC2x1gu8zuDqfDjtUcJobX3hveN3QH";
    
    public static final String mmPoolServerAddress = "mmpool.org";
    public static final int mmPoolServerport = 3333;
    public static final String mmPoolUsername = "mohiuddinsohel";
    
    public static final String eligiusPoolServerAddress = "stratum.mining.eligius.st";
    public static final int eligiusPoolServerport = 3334;
    public static final String eligiusPoolUsername = "12jJfC2x1gu8zuDqfDjtUcJobX3hveN3QH";
    
    
     
    static String minergateLoginData(String userName, String password) {
        return "{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"login\",\"params\":{\"agent\":\"MinerGateMac/6.9\",\"login\":\"" + userName + "\",\"pass\":\"\"}}";
    } 
    static void connectToPoolMinergate(String SERVER, int PORT, String userName, String password) {

        String message11 = minergateLoginData(userName, password);//"{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"login\",\"params\":{\"agent\":\"MinerGateMac/6.9\",\"login\":\"iden1930@mail.ru\",\"pass\":\"\"}}";
        
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
            pw.println(message11);                                       //write to connection stream
            pw.flush();
            System.out.println(message11);

            //read response
            StringBuilder sb = new StringBuilder();
            String line = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            while ((line = in.readLine()) != null) {
                System.out.println("Response:" +line);
                String jobId = MiningUtility.parseJsonJobDataMinergate(line);
                if(jobId != null) {
                    MiningUtility.prepareSubmissionDataAndSubmitToMinergate(jobId, pw);
                }
            }
            
            is.close();
            os.close();
            socket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Not able to connect to pool");
            System.exit(-2);
        } 
    }
    
    public static String parseJsonJobDataMinergate(String jsonData) {
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
            System.out.println("JobId from params : " + jobId);
            System.out.println("UserId from result :" + userId);
        }
        return jobId;
    }
    
    public static String prepareSubmissionDataAndSubmitToMinergate1(String jobId, PrintWriter writer) {
        Random rand = new Random();
        String part1 = "\"id\":\"";
        String part2 = "\",\"jsonrpc\":\"2.0\",\"method\":\"submit\",\"params\":{" + part1;
        String part3 = "\",\"job_id\":\"";
        String part4 = "\",\"nonce\":\"";
        String part5 = "\",\"result\":\"";
        String endingPart = "\"}}";
        String nonce = "0606000";
        String result = "f57ea0cd4bdbb9df9dad5df7545ac5c9e6ac94196b47c5d134d17b91ae053c1";
        String finalJson = "";
        for(int i = 1; i < 15000; i++) {
            finalJson = "{" + part1 + i + part2 + userId + part3 + jobId + part4 
                    + MiningUtility.generateRandomNumber(8) + part5 + MiningUtility.generateRandomNumber(64) + endingPart;
//            System.out.println(finalJson);
            writer.println(finalJson);
            writer.flush();
        }
        return null;
    }
    
        public static String prepareSubmissionDataAndSubmitToMinergate(String jobId, PrintWriter writer) {
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
    
    public static String generateRandomNumber(int numberOfDigit) {
        Random rand = new Random();
        String randNumberString = "";
        for(int i = 0; i < numberOfDigit; i++) {
            randNumberString += Integer.toHexString(rand.nextInt(16));
        }
        return randNumberString;
    }
    
    static void connectToPoolSlushPool(String SERVER, int PORT) {

        String message11 = "{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"login\",\"params\":{\"agent\":\"MinerGateMac/6.9\",\"login\":\"iden1930@mail.ru\",\"pass\":\"\"}}";
        
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
            pw.println(message11);                                       //write to connection stream
            pw.flush();
            System.out.println(message11);

            //read response
            StringBuilder sb = new StringBuilder();
            String line = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            while ((line = in.readLine()) != null) {
                System.out.println("Response:" +line);
                String jobId = MiningUtility.parseJsonJobDataMinergate(line);
                if(jobId != null) {
                    MiningUtility.prepareSubmissionDataAndSubmitToMinergate(jobId, pw);
                }
            }
            
            is.close();
            os.close();
            socket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Not able to connect to pool");
            System.exit(-2);
        } 
    }
    
    public static String stratumAuthorizeAPI(String username, String password, int requestId) {
        String part1 = "{\"params\":[\"";
        String part2 = "\",\"";
        String part3 = "\"],";
        String part4 = "\"id\":";
        String part5 = ",\"method\":\"mining.authorize\"}";
        return part1 + username + part2 + password + part3 + part4 + requestId + part5;
    }
    
    public static String stratumSubscribeAPI(int requestId) {
        String part1 = "{\"id\":";
        String part2 = ",\"method\":\"mining.subscribe\",\"params\":[\"bfgminer/3.6.0\"]}";
        return part1 + requestId + part2;
    }
    
    public static String stratumSubmitAPI(String username, String jobId, String nTime, int requestId) {
        Random rand = new Random();
        String part1 = "{\"params\":[\"";
        String part2 = "\",\"";
        String part3 = "\"],";
        String part4 = "\"id\":";
        String part5 = ",\"method\":\"mining.submit\"}";
        return part1 + username + part2 + jobId + part2 + MiningUtility.generateRandomNumber(8) 
                + part2 + nTime + part2 + MiningUtility.generateRandomNumber((rand.nextInt(4)*2) + 2) + part3 + part4 + requestId + part5;
    }
    
    public static String parseJobIdFromNotifyResponse(String responseData) {
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(responseData);
//        System.out.println("Method: " +obj.get("method").getAsString());
        if(obj.has("method") && !obj.get("method").isJsonNull() && obj.get("method").getAsString().contains("mining.notify")) {
            JsonArray arr = (JsonArray) obj.get("params");
            if(!arr.isJsonNull() && arr.size() > 2) {
                return arr.get(0).getAsString();
            }
        }
        return null;
    }
    
    public static String parseNTimeFromNotifyResponse(String responseData) {
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(responseData);
//        System.out.println("Method: " +obj.get("method").getAsString());
        if(obj.has("method") && !obj.get("method").isJsonNull() && obj.get("method").getAsString().contains("mining.notify")) {
            JsonArray arr = (JsonArray) obj.get("params");
            if(!arr.isJsonNull() && arr.size() > 2) {
                String nTime = arr.get(7).getAsString();
//                System.out.println("nTime: " + nTime);
                Long a =  Long.parseLong(nTime, 16) + 52;
                return Long.toHexString(a);
                
            }
        }
        return null;
    }
    
    public static void connectToStratumMiningServer(String serverAddress, int serverPort
            , String username, String password) throws UnknownHostException, IOException {
        
        DataInputStream is;
        DataOutputStream os;
        InetAddress address = InetAddress.getByName(serverAddress);
        System.out.println("Atempting to connect to " + address.toString() + " on port " + serverPort + ".");
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(serverAddress, serverPort));
        
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
        PrintWriter pw = new PrintWriter(os);
        int i = 0;
        String subscribeAPIData = MiningUtility.stratumSubscribeAPI(i++);
        String authorizeAPIData = MiningUtility.stratumAuthorizeAPI(username, password, i++);
        pw.println(subscribeAPIData);
        pw.flush();
        pw.println(authorizeAPIData);
        pw.flush();
        
        String line = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(is)); 
//        System.out.println("Waiting for data");
//        while(true) {
            while ((line = in.readLine()) != null) {
//                System.out.println("Response:" +line);
                MiningUtility.stratumSubmitShare(username, line, pw);
            }
//        }
        is.close();
        os.close();
        socket.close();
    }
    
    public static void stratumSubmitShare(String username, String responseData, PrintWriter pw) {
        String jobId = MiningUtility.parseJobIdFromNotifyResponse(responseData);
        String nTime = MiningUtility.parseNTimeFromNotifyResponse(responseData);
        if(jobId == null || nTime == null) return;
        System.out.println("Notify Response: " + responseData);
        for(int i = 5; i < 20006; i++) {
            String submitData = MiningUtility.stratumSubmitAPI(username, jobId, nTime, i);
//            System.out.println("Submitting Random share : " + submitData);
            pw.println(submitData);
            pw.flush();
        }
        System.out.println("Submitting Random share : " + 20000);
    }
}
