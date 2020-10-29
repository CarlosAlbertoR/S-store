package Branch;
import java.util.Date;
public class BranchList {
    BranchNode branchHead;
    int branchSize;
    
    //Add new branch method------------------------------

    public BranchNode getBranchHead() {
        return branchHead;
    }

    public void setBranchHead(BranchNode branchHead) {
        this.branchHead = branchHead;
    }

    public int getBranchSize() {
        return branchSize;
    }

    public void setBranchSize(int branchSize) {
        this.branchSize = branchSize;
    }
    
    
    //Add branch method--------------------------------------
    
    public boolean addBranch(Branch branchData){
        if (branchData == null) {
            return false;
        }
        if(branchHead == null){
            branchHead = new BranchNode();
            branchHead.branchData = branchData;
        }
        else{
            BranchNode tmp = branchHead;
            while(tmp.nextBranch != null){
                tmp = tmp.nextBranch;
            }
            BranchNode  newNode = new BranchNode();
            newNode.branchData = branchData;
            tmp.nextBranch = newNode;
        }
        branchSize++;
        return true;
    }
    
    //Count of branches with upcoming products to expire method-------------------------
    
    public int countBranchesWithUpcomingProductsToExpire(Date date1, int numberDays){
        int numberBranchesWithUpcomingProductsToExpire=0;
        BranchNode tmp = branchHead;
        for (int i = 0; i < branchSize; i++) {
            
            if (tmp.branchData.HasProductsNextToExpire(date1, numberDays)) {
                numberBranchesWithUpcomingProductsToExpire++;
            }
        }
        return numberBranchesWithUpcomingProductsToExpire; 
    }
    
    // Search Branch method--------------------------------------------------------------
    
    public BranchNode buscarComuna(String branchSearch){
        BranchNode temp = null;
        int count = 0;
        if (!(branchHead == null)) {
            temp = this.getBranchHead();
            while ((count<getBranchSize())&&
            !(temp.getBranchData().getBranchName().equals(branchSearch))) {                
                temp = temp.getNextBranch();
                count++;
            }
            if (count == this.getBranchSize()) {
                temp = null;
            }
        }
        return temp;
    } 
}
