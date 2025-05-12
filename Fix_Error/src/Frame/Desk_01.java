public class Desk_01 {
    private String prodctid;
    private String deskid;
    private String diskdata;
    private String price;

    public Desk_01(String prodctid, String deskid, String diskdata, String price) {
        this.prodctid = prodctid;
        this.deskid = deskid;
        this.diskdata = diskdata;
        this.price = price;
    }

    public String getProdctid() { return prodctid; }
    public String getDeskid() { return deskid; }
    public String getDiskdata() { return diskdata; }
    public String getPrice() { return price; }
}