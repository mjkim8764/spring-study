package main;

import assembler.Assembler;
import spring.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForAssembler {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("명령어를 입력하세요:");
            String command = reader.readLine();
            if(command.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            }


            // command.split(" ") -> {"new", "a@a.com", "이름", "암호", "암호"}
            // 파라미터를 구분자로 이용해서 콘솔에서 입력받은 문자열을 배열로 만듬.

            if(command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
            } else if(command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
                continue;
            }

            printHelp();

        }
    }



    /**
     * Assembler 클래스는 의존 대상이 되는 객체들을 주입시키는 클래스이기 때문에
     * Assembler 객체를 생성한 시점에서 사용할 객체가 모두 생성되는 것.
     */

    private static Assembler assembler = new Assembler();


    // new 커맨드 처리를 위한 메서드
    private static void processNewCommand(String[] arg) {
        if (arg.length != 5) {
            printHelp();
            return;
        }

        MemberRegisterService regSvc = assembler.getMemberRegisterService();
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        if(!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인이 일치하지 않습니다.\n");
            return;
        }

        try {
            regSvc.regist(req);
            System.out.println("등록했습니다.\n");
        } catch (DuplicateMemberException e){
            System.out.println("이미 존재하는 이메일입니다.\n");
        }
    }


    // change 커맨드 처리를 위한 메서드
    private static void processChangeCommand(String[] arg){
        if (arg.length != 4) {
            printHelp();
            return;
        }

        ChangePasswordService changePwdSvc = assembler.getChangePasswordService();

        try {
            changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("암호를 변경했습니다. \n");
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다. \n");
        } catch (WrongIdPasswordException e) {
            System.out.println("이메일과 암호가 일치하지 않습니다. \n");
        }
    }

    // 입력 형식이 잘못됐을 때 출력되는 도움말
    private static void printHelp() {
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
        System.out.println("명령어 사용법 : ");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번 변경비번");
        System.out.println();
    }
}
