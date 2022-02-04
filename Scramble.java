package com.example.rubikscube;

import java.util.Random;
import java.util.*;

public class Scramble {



    String scrambleSign() {
        String[] scramble = new String[20];

        //18通りの回し方があるのでランダムで18この数値を取り出す
        Random random = new Random();

        //18通りの記号を箱に入れる
        String[] sc = {"R", "R2", "R'", "L", "L2", "L'", "U", "U2", "U'", "D", "D2", "D'", "F", "F2", "F'", "B", "B2", "B'"};
        int[] num = new int[20];

        //スクランブルは20手順回すので20回for文を繰り返す
        //scramble配列に記号を入れていく
        a:for(int i = 0 ; i < 20 ; i++) {
            int n = random.nextInt(18);//0~17
            num[i] = n;//num配列にrandomで得た数値を代入する

            if(i != 0) {//記号の一つ手前を見ている
                if(num[i-1] == n) {//randomで前回と同じ数値がでた場合キャンセル
                    i--;
                    continue;
                }

                if((n+1) % 3 == 0) {//R', L', U', D', F', B'がでたとき
                    if(num[i-1] == (n-1) || num[i-1] == (n-2)) {//記号の同類がでたとき　例）1回目がR'で2回目がRまたはR2
                        i--;
                        continue;
                    }else if(i != 1) {//記号の二つ手前を見ている
                        if((n+1)%6 == 3) {//R', U', F'がでたとき
                            for(int j = -3 ; j < 3 ;j++) {//2回前に同じ方向の記号が出たとき　例）今回がR'で2回前がL,L2,L',R,R2,R'
                                if(num[i-2] == (n-j)) {
                                    i--;
                                    continue a;
                                }
                            }

                        }else if((n+1)%6 == 0) {//L', D', B'がでたとき
                            for(int j = 0 ; j < 6 ;j++) {//2回前に同じ方向の記号が出たとき　例）今回がL'で2回前がR,R2,R',L,L2,L'
                                if(num[i-2] == (n-j)) {
                                    i--;
                                    continue a;
                                }
                            }
                        }

                    }
                }

                if((n+1) % 3 == 2) {//R2, L2, U2, D2, F2, B2がでたとき
                    if(num[i-1] == (n-1) || num[i-1] == (n+1)) {//記号の同類がでたとき　例）1回目がR2で２回目がRまたはR'
                        i--;
                        continue;
                    }else if(i != 1) {//記号の二つ手前を見ている
                        if((n+1)%6 == 2) {//R2, U2, F2がでたとき
                            for(int j = 4 ; j < -2 ;j++) {//2回前に同じ方向の記号が出たとき　例）今回がR2で2回前がL,L2,L',R,R2,R'
                                if(num[i-2] == (n-j)) {
                                    i--;
                                    continue a;
                                }
                            }

                        }else if((n+1)%5 == 0) {//L2, D2, B2がでたとき
                            for(int j = -1 ; j < 5 ;j++) {//2回前に同じ方向の記号が出たとき　例）今回がL2で2回前がR,R2,R',L,L2,L'
                                if(num[i-2] == (n-j)) {
                                    i--;
                                    continue a;
                                }
                            }
                        }

                    }
                }

                if((n+1) % 3 == 1) {//R, L, U, D, F, Bがでたとき
                    if(num[i-1] == (n+1) || num[i-1] == (n+2)) {//記号の同類がでたとき　例）1回目がRで２回目がR2またはR'
                        i--;
                        continue;
                    }else if(i != 1) {//記号の二つ手前を見ている
                        if((n+1)%6 == 1) {//R, U, Fがでたとき
                            for(int j = -5 ; j < 1 ;j++) {//2回前に同じ方向の記号が出たとき　例）今回がRで2回前がL,L2,L',R,R2,R'
                                if(num[i-2] == (n-j)) {
                                    i--;
                                    continue a;
                                }
                            }

                        }else if((n+1)%4 == 0) {//L, D, Bがでたとき
                            for(int j = -2 ; j < 4 ;j++) {//2回前に同じ方向の記号が出たとき　例）今回がLで2回前がR,R2,R',L,L2,L'
                                if(num[i-2] == (n-j)) {
                                    i--;
                                    continue a;
                                }
                            }
                        }

                    }
                }

            }



            scramble[i] = sc[n];

        }

        String str = Arrays.toString(scramble);
        return str;
    }


}
