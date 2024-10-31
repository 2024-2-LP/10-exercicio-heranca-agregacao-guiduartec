package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
        this.desenvolvedores = new ArrayList<>();
    }

    public Consultoria() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Boolean contratar(Desenvolvedor desenvolvedor) {
        if (desenvolvedores.size() >= vagas) {
            return false;
        }
        desenvolvedores.add(desenvolvedor);
        return true;
    }

    public Boolean contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if (desenvolvedor.isFullstack()) {
            contratar(desenvolvedor);
            return true;
        }
        return false;
    }

    public Double getTotalSalarios() {
        Double salarioTotal = 0.0;
        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            salarioTotal += desenvolvedor.calcularSalario();
        }
        return salarioTotal;
    }

    public Integer qtdDesenvolvedoresMobile() {
        Integer qtdMobile = 0;
        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            if (desenvolvedor instanceof DesenvolvedorMobile) {
                qtdMobile++;
            }
        }
        return qtdMobile;
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario) {
        List<Desenvolvedor> devMaiorSalario = new ArrayList<>();
        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            if (desenvolvedor.calcularSalario() >= salario) {
                devMaiorSalario.add(desenvolvedor);
            }
        }
        return devMaiorSalario;
    }

    public Desenvolvedor buscarMenorSalario() {
        if (desenvolvedores.isEmpty()) {
            return null;
        }
        Desenvolvedor desenvolvedorMenorSalario = desenvolvedores.get(0);
        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            if (desenvolvedor.calcularSalario() < desenvolvedorMenorSalario.calcularSalario()) {
                desenvolvedorMenorSalario = desenvolvedor;
            }
        }
        return desenvolvedorMenorSalario;
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        List<Desenvolvedor> dev = new ArrayList<>();
        for (Desenvolvedor desenvolvedorDaVez : desenvolvedores) {
            if (desenvolvedorDaVez instanceof DesenvolvedorMobile) {
                if (((DesenvolvedorMobile) desenvolvedorDaVez).getPlataforma().equals(tecnologia) || ((DesenvolvedorMobile) desenvolvedorDaVez).getLinguagem().equals(tecnologia)) {
                    dev.add(desenvolvedorDaVez);
                }
            }
            if (desenvolvedorDaVez instanceof DesenvolvedorWeb) {
                if (((DesenvolvedorWeb) desenvolvedorDaVez).getBackend().equals(tecnologia) || ((DesenvolvedorWeb) desenvolvedorDaVez).getFrontend().equals(tecnologia) || ((DesenvolvedorWeb) desenvolvedorDaVez).getSgbd().equals(tecnologia)) {
                    dev.add(desenvolvedorDaVez);
                }
            }
        }
        return dev;
    }

    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        List<Desenvolvedor> retornar = buscarPorTecnologia(tecnologia);
        Double contador = 0.0;
        for (Desenvolvedor desenvolvedor : retornar) {
            contador += desenvolvedor.calcularSalario();
        }
        return contador;
    }

}
