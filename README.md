# Poisoning Attack on Mining Pool
This project is used to perform poisoning attack on different mining pools which is published in Data Privacy Management, Cryptocurrencies and Blockchain Technology workshop, ESORICS 2018. By deliberately introducing errors under benign miners’ names, this attack can fool the mining pool administrator into punishing any innocent miner; when the top miners are punished, this attack can significantly slow down the overall production of the mining pool. This project experimentally confirms the the effectiveness of this attack scheme against well-known mining pools.

### Supported Mining Pool during the publication
1. Slushpool: slushpool.com
2. Kanopool: kano.is
3. F2pool: f2pool.com
4. Mmpool: mmpool.org
5. EligiusPool: stratum.mining.eligius.st
6. Bixin: stratum.bixin.com
7. OzCoinPool: spare.ozco.in
8. Multipool: us.multipool.us
9. Minergate: pool.minergate.com
10. GiveMeCoins: give-me-coins.com
11. AntPool: antpool.com

### Requirements to run the project
1. This a Java project which requires JDK 7 at least.
2. Currently different pool address, port number, username and password are hard coded.
3. To run it for your specific pool of choice, You have to set it by yourself in the MiningUtility class
#### OR
You have to call the following method from main method with the required parameter:
> **MiningUtility.connectToStratumMiningServer(poolServerAddress, poolServerPortNumber, username, password)**
where default password for mining in most of the pools are empty string.

### Publication Link
[Mohiuddin Ahmed, Jinpeng Wei, Wang Y., Ehab Al-Shaer. (2018) A Poisoning Attack Against Cryptocurrency Mining Pools. In: ESORICS 2018 International Workshops, Data Privacy Management, Cryptocurrencies and Blockchain Technology. DPM 2018, CBT 2018](https://doi.org/10.1007/978-3-030-00305-0_11)
