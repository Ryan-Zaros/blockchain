import java.util.Date;

public class Block {
    
    private String data;
    public String hash;
	public String previousHash;
	private long timeStamp;
    private int num;

    // Constructor
    public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
	}

    public String calculateHash() {
        String calculatedhash = StringUtils.applySha256( 
                previousHash 
                + Long.toString(timeStamp) 
                + Integer.toString(num)
                + data);
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            num++;
            hash = calculateHash();
        }
        System.out.println("Block mined: " + hash);
    }
}