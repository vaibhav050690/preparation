package others;

public class SearchAWordIn2DGridOfCharacters {

    int row;
    int col;

    int[] x = {-1,-1,-1,0,0,1,1,1};
    int[] y = {-1,0,1,-1,1,-1,0,1};

    char[][] grid;

    public SearchAWordIn2DGridOfCharacters(char[][] grid, int row, int col){
        this.grid = grid;
        this.row = row;
        this.col = col;
    }

    public boolean searchWord(String word, int rowIndex, int colIndex){
        if(grid[rowIndex][colIndex] != word.charAt(0)){
            return false;
        }
        for(int dir =0; dir < 8; dir++){
            int rd = rowIndex + x[dir];
            int cd = colIndex + y[dir];
            int k =1;
            for(; k<word.length(); k++){
                if(rd < row && rd>=0 && cd>=0 && cd < col){
                    if(grid[rd][cd] != word.charAt(k)){
                        break;
                    }
                }
                else {
                    break;
                }
                rd+= x[dir];
                cd+= y[dir];
            }
            if(k == word.length()){
                return true;
            }
        }
        return false;
    }

    public void searchWordInGrid(String word){
        for(int i =0; i< row; i++){
            for(int j =0; j<col; j++){
                if(searchWord(word,i,j)){
                    System.out.println("Word found at " + i + "," + j);
                }
            }
        }
    }

    public static void main(String[] args) {
        int row = 3;
        int col = 13;
        char grid[][] = { "GEEKSFORGEEKS".toCharArray(),
                          "GEEKSQUIZGEEK".toCharArray(),
                          "IDEQAPRACTICE".toCharArray()
        };
        SearchAWordIn2DGridOfCharacters search = new SearchAWordIn2DGridOfCharacters(grid,row,col);
        search.searchWordInGrid("GEEKS");
    }
}