package application;

import model.entities.Athlete;
import model.exceptions.AthleteException;

import java.util.*;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Qual a quantidade de atletas? ");
            int qnt = sc.nextInt();

            List<Athlete> athleteList = new ArrayList<>();

            for (int i = 0; i < qnt; i++) {
                sc.nextLine();
                System.out.println("Digite os dados do atleta numero " + (i + 1) + ":");

                System.out.print("Nome: ");
                String name = sc.nextLine();

                System.out.print("Sexo: ");
                char gender = sc.next().charAt(0);

                System.out.print("Altura (digite no formato m.cm): ");
                double height = sc.nextDouble();

                System.out.print("Peso: ");
                double weight = sc.nextDouble();

                Athlete athlete = new Athlete(name, gender, height, weight);

                athlete.validate(name,gender,height,weight);

                athleteList.add(athlete);

                System.out.println();

                System.out.println("RELATÓRIO:");
                System.out.print("Peso medio dos atletas: ");
                System.out.println(String.format("%.2f", middleWeight(athleteList)));
                System.out.print("Atleta mais alto: ");
                System.out.println(higher(athleteList));
                System.out.print("Porcentagem de homens: ");
                System.out.println(String.format("%.1f %%", menPercentage(athleteList)));

                if (womenCalc(athleteList) == null) {
                    System.out.println("Não há mulheres cadastradas");
                } else {
                    System.out.print("Altura média das mulheres: ");
                    System.out.printf("%.2f %n", womenCalc(athleteList));
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.print("O dado informado está errado. "+ e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Digite um dado valido.");
        } catch (AthleteException e) {
            System.out.println(e.getMessage());
        }
        finally {
            sc.close();
        }
    }

    public static Double middleWeight(List<Athlete> list) {
        int qnt = 0;
        double allWeight = 0;
        for (Athlete c : list) {
            qnt++;
            allWeight += c.getWeight();
        }
        return allWeight / qnt;
    }

    public static String higher(List<Athlete> list) {
        double higher = 0;
        String higherName = null;

        for (Athlete c : list) {
            if (c.getHeight() > higher) {
                higher = c.getHeight();
                higherName = c.getName();
            }
        }

        return higherName;
    }

    public static double menPercentage(List<Athlete> list) {
        int qnt = 0;
        int mans = 0;

        for (Athlete c : list) {
            qnt++;
            if (c.getGender() == 'M') {
                mans += 1;
            }
        }
        return ((double) mans / qnt) * 100;
    }

    public static Double womenCalc(List<Athlete> list) {
        int women = 0;
        double totalHeight = 0;

        for (Athlete c : list) {
            if (c.getGender() == 'F') {
                women++;
                totalHeight += c.getHeight();
            }
        }

        if (women == 0) {
            return null;
        } else {
            return totalHeight / women;
        }
    }

}

