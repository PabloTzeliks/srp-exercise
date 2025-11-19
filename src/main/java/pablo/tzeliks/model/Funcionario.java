package pablo.tzeliks.model;

public class Funcionario {

    private String nome;
    private double salarioBase;
    private String cargo;
    private Sindicato sindicato;

    public Funcionario(String nome, double salarioBase, String cargo, Sindicato sindicato) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.cargo = cargo;
        this.sindicato = sindicato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Sindicato getSindicato() {
        return sindicato;
    }

    public void setSindicato(Sindicato sindicato) {
        this.sindicato = sindicato;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                "\nSalario Base: R$" + salarioBase +
                "\nCargo: " + cargo +
                "\nSindicato: " + sindicato;
    }
}