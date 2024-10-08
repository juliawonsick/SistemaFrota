// TRABALHO EM DULPA: JÚLIA WONSICK RA: 1136562 E LAURA CEMIN IORA RA: 1131015
public final class Moto extends Veiculo {
    private boolean partidaEletrica;

    @Override
    public String toString() {
        String partida = this.getPartidaEletrica() ? "Sim" : "Não";
        return super.toString() + " - Part. Elétrica: " + partida;
    }

    public boolean getPartidaEletrica() {
        return partidaEletrica;
    }

    public void setPartidaEletrica(boolean partidaEletrica) {
        this.partidaEletrica = partidaEletrica;
    }

    public Moto(String marca, String modelo, int ano, String placa, boolean partidaEletrica) {
        super(marca, modelo, ano, placa);
        this.partidaEletrica = partidaEletrica;
    }

    @Override
    public double calcularImposto() {
        return 500.0;
    }
}

