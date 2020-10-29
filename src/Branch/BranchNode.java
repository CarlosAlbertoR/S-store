package Branch;

public class BranchNode {
    Branch branchData;
    BranchNode nextBranch;

    public Branch getBranchData() {
        return branchData;
    }

    public void setBranchData(Branch branchData) {
        this.branchData = branchData;
    }

    public BranchNode getNextBranch() {
        return nextBranch;
    }

    public void setNextBranch(BranchNode nextBranch) {
        this.nextBranch = nextBranch;
    }
}
