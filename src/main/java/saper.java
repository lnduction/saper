import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class saper extends JFrame implements ActionListener {
    private int[][] pole;
    private JButton reset = new JButton(  );
    private Label time = new Label(  );
    private Timer timer = new Timer(1000, this);
    private JButton key_botton[][];
    private boolean status;
    private int schet;
    private int boom;
    private int Seck = 0;
    private int Min = 0;
    private JButton flag = new JButton(  );
    private int flaged[] = new int [2];
    private boolean StstusFlag = false;
    saper(int a, int b, int m){

        status = true;
        schet = 0;
        boom = m;
        timer.start();

        pole = new int[a][b];
        key_botton = new JButton[a][b];
        setSize(    50 * a,  b * 50 + 125);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            Action action = new Action();
        int count = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
             key_botton[i][j] = new JButton();
             key_botton[i][j].addActionListener(action);
             key_botton[i][j].setBounds( 50 * j,   50 * i + 100, 50, 50);
             key_botton[i][j].setActionCommand( Integer.toString( count ));
               add(key_botton[i][j]);
                count++;
            }

            setLayout( null );
            setVisible( true );
            setResizable( true );
        }




        time.setBounds( a*25 - 70,50,250,50 );

        add( time );

        //

Action_reset action_reset = new Action_reset();

        reset.setText("reset");
        reset.setBounds( a * 50 - 100, 0, 100, 25 );
        reset.addActionListener( action_reset );
        add(reset);

        //
        Action_flag action_flag = new Action_flag();
        flag.setText( "flag" );
        flag.setBounds( 0,0,100,25 );
        flag.addActionListener( action_flag );
        add(flag);

        //
        Font font = new Font("San Francisco", Font.PLAIN, 35);
        Font font2 = new Font("San Francisco", Font.PLAIN, 40);

        time.setFont( font2);
        int att = 0;
        while (att < m) {
                Random random = new Random();
                int i_m = random.nextInt( a );
                int j_m = random.nextInt( b );
                if (pole[i_m][j_m] != -1) att++;
                pole[i_m][j_m] = -1;
        }

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if(pole[i][j]==-1) continue;
                try
                    {
                    for (int k = i - 1; k < i + 2; k++) {
                        if (k < 0 || k >= a) continue;
                        for (int l = j - 1; l < j + 2; l++) {
                            if (l < 0 || l >= b) continue;
                            if (pole[k][l] == -1) pole[i][j]++;
                        }

                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("error");
                }
                }
            }
        for (int i = 0; i < a ; i++) {
            for (int j = 0; j < b ; j++) {
                System.out.print(pole[i][j] + "\t");
            }
            System.out.println();

        }
        System.out.println();
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        String a;
        if (Min < 10 && Seck >= 10)  a =  "0" + Min + " : " + Seck ;
        else if (Min >= 10 && Seck < 10)  a =  Min + " : " + "0" + Seck ;
        else if (Min < 10 && Seck < 10)   a =  "0" + Min + " : " + "0" + Seck;
        else if (Min >= 10 && Seck >= 10)  a =   Min + " : " + Seck ;
        else a = "error";
       time.setText( a );
       if(Seck == 59) {
           Min++;
           Seck = 0;
       } else Seck++;


    }

    public class Action_flag implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!StstusFlag) {
                StstusFlag = true;
                flag.setText( "flag on" );
            } else {
                StstusFlag = false;
                flag.setText( "flag off" );
            }
        }
    }

    public class Action_reset implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < pole.length; i++) {
                for (int j = 0; j <pole[1].length ; j++) {
                    key_botton[i][j].setText( "" );
                }
            }

            for (int i = 0; i < pole.length ; i++) for (int j = 0; j < pole[1].length ; j++) pole[i][j] = 0;
                    int att = 0;
                    while (att < boom)
             {
                Random random = new Random(  );
                int i_m =  random.nextInt(pole.length);
                int j_m = random.nextInt(pole[1].length);
               if (pole[i_m][j_m] != -1) att++;
                    pole[i_m][j_m] = -1;


            }
            for (int i = 0; i < pole.length; i++) {
                for (int j = 0; j < pole[1].length; j++) {
                    if(pole[i][j]==-1) continue;
                        for (int k = i - 1; k < i + 2; k++) {
                            if (k < 0 || k >= pole.length) continue;
                            for (int l = j - 1; l < j + 2; l++) {
                                if (l < 0 || l >= pole[1].length) continue;
                                if (pole[k][l] == -1) pole[i][j]++;
                            }
                    }
                }
            }
            for (int i = 0; i < pole.length ; i++) {
                for (int j = 0; j < pole[1].length ; j++) {
                    System.out.print(pole[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println();

            schet = 0;
            status = true;
            flaged[0] = flaged[1] = 0;
            Seck = 0;
            Min = 0;
            timer.start();
        }
    }


    public class Action implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        if (StstusFlag  && status) {
    if (pole[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length] == -1 &&
            key_botton[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length].getText( ) != "BOMB") {
        flaged[0]++;
      //  System.out.println(flaged[0] + "\t" + flaged[1]);
    }
    else {
        if (key_botton[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length].getText( ) != "BOMB") {
            flaged[1]++;
          //  System.out.println( flaged[0] + "\t" + flaged[1] );
        }
    }
            key_botton[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length].setText( "BOMB" );
} else {
            if (key_botton[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length].getText( ) == "BOMB"){
                key_botton[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length].setText( "" );
                if (pole[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length] == -1) {
                    flaged[0]--;
                   // System.out.println(flaged[0] + "\t" + flaged[1]);
                }
                else {
                    flaged[1]--;
                  //  System.out.println(flaged[0] + "\t" + flaged[1]);
                }
            } else {
                if (pole[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length] == -1 && status == true) {
                    for (int i = 0; i < pole.length; i++) {
                        for (int j = 0; j < pole[1].length; j++) {
                            key_botton[i][j].setText( Integer.toString( pole[i][j] ) );
                            status = false;
                            schet = 0;
                            timer.stop();
                            Font font = new Font("San Francisco", Font.PLAIN, 40);
                            time.setFont( font );
                            time.setText( "DEAD!" );
                        }
                    }
                } else if (status) {
                    if (key_botton[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length].getText() == "" &&
                            pole[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length] != 0)
                        schet++;
                    if (pole[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length] == 0) {
                        boolean statusZero = false;

                        for (int i = 0; i < pole.length; i++) {
                            for (int j = 0; j < pole[1].length; j++) {
                                for (int k = i - 1; k < i + 1; k++) {
                                    if (k < 0 || k >= pole.length) continue;
                                    for (int l = j - 1; l < j + 1; l++) {
                                        if (l < 0 || l >= pole[1].length) continue;
                                        if (pole[k][l] == 0) statusZero = true;
                                    }
                                }
                                if (statusZero) {
                                    for (int k = i - 1; k < i + 1; k++) {
                                        if (k < 0 || k >= pole.length) continue;
                                        for (int l = j - 1; l < j + 1; l++) {
                                            if (l < 0 || l >= pole[1].length) continue;
                                            if (key_botton[k][l].getText() != "") continue;
                                            key_botton[k][l].setText( Integer.toString( pole[k][l] ) );
                                            schet++;
                                        }
                                    }
                                    statusZero = false;
                                }

                            }
                        }
                    } else {
                        key_botton[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length].
                                setText( Integer.toString( pole[Integer.parseInt( e.getActionCommand() ) / pole[1].length][Integer.parseInt( e.getActionCommand() ) % pole[1].length] ) );
                    }
                }
            }
        }
        if ((schet == pole.length * pole[1].length - boom )|| (flaged[0] == boom && flaged[1] == 0)) {
            for (int i = 0; i < pole.length; i++) {
                for (int j = 0; j < pole[1].length; j++) {
                    key_botton[i][j].setText( Integer.toString( pole[i][j] ) );
                    status = false;
                    schet = 0;
                    timer.stop();
                    Font font = new Font("San Francisco", Font.PLAIN, 40);
                    time.setFont( font );
                    time.setText( "alive!" );
                }
            }
        }

    }
}
}

