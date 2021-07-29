package br.com.rogersdk.calculadorajni;

public class OperacaoTO {
    private int x;
    private int y;
    private TipoOperacao operador;
    private int resultado;

    public OperacaoTO() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TipoOperacao getOperador() {
        return operador;
    }

    public void setOperador(TipoOperacao operador) {
        this.operador = operador;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "OperacaoTO{" +
                "x=" + x +
                ", y=" + y +
                ", operador=" + operador +
                '}';
    }
}
