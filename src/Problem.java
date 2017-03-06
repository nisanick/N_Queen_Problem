/**
 * Created by Robert Hudec on 25/01/2017.
 */
public class Problem {

    public static final int DEFAULT_BOARD_SIZE = 8;

    public static void main(String[] args) {
        int size = DEFAULT_BOARD_SIZE;
        if(args.length > 0){
            try{
                size = Integer.parseInt(args[0]);
            }catch (NumberFormatException ex){
                System.out.println("Entered value is not a number! Using default setting.");
            }
        }
        Problem problem = new Problem(size);
        long start = System.currentTimeMillis();
        problem.solve();
        long end = System.currentTimeMillis();
        System.out.println(problem.getResult());
        System.out.println("Solved in " + (end - start) + "ms.");
    }


    private int board_size;
    private int[] queens;
    private int solutions;


    public Problem(int board_size) {
        this.board_size = board_size;
        if(board_size < 0) this.queens = new int[0];
        else this.queens = new int[board_size];
        this.solutions = 0;
    }


    public void solve() {

        if(this.board_size < 1){
            this.solutions = 0;
            return;
        }

        if (this.board_size == 1) {
            this.solutions = 1;
            return;
        }

        int index = 0;

        while(this.queens[0] < this.board_size / 2){


            if(this.queens[index] >= this.board_size){
                this.queens[--index]++;
            }

            if(isThreatened(index)){
                if(++this.queens[index] >= this.board_size){
                    this.queens[--index]++;
                }
            } else {
                if(index == this.board_size -1){
                    this.solutions += 2;
                    if(++this.queens[index] >= this.board_size){
                        this.queens[--index]++;
                    }
                } else {
                    this.queens[++index] = 0;
                }
            }
        }

        if(this.board_size % 2 == 1){
            while(this.queens[0] == this.board_size / 2){

                if(this.queens[index] >= this.board_size){
                    this.queens[--index]++;
                }

                if(isThreatened(index)){
                    if(++this.queens[index] >= this.board_size){
                        this.queens[--index]++;
                    }
                } else {
                    if(index == this.board_size -1){
                        this.solutions += 1;
                        if(++this.queens[index] >= this.board_size){
                            this.queens[--index]++;
                        }
                    } else {
                        this.queens[++index] = 0;
                    }
                }
            }
        }
    }


    private boolean isThreatened(int threatened){

        int threatening = threatened - 1;

        while(threatening >= 0){
            if(threatening == threatened)
                continue;
            int threat_column = this.queens[threatened];
            int threat_left_diagonal = threat_column - Math.abs(threatening - threatened);
            int threat_right_diagonal = threat_column + Math.abs(threatening - threatened);
            int threat_position = this.queens[threatening];


            if(threat_column == threat_position || threat_right_diagonal == threat_position || threat_left_diagonal == threat_position)
                return true;

            threatening--;
        }

        return false;
    }

    /**
     * Tato metoda nam poskytne vysledok riesenia problemu v citatelnej forme.
     * @return slovny popis vysledku
     */
    public String getResult() {
        return String.format("You can place %d queens on %dx%d board %d different ways.", this.board_size, this.board_size, this.board_size, solutions);
    }

    public int getSolutions() {
        return solutions;
    }
}