package com.example.simuladorclinica.ecuDiferencial;

public class RungeKutta {
  private float xInicial;
  private float yInicial;
  private final String funcion;
  private float h;

  public RungeKutta(float xInicial, float yInicial, float h, String funcion) {
    this.xInicial = xInicial;
    this.yInicial = yInicial;
    this.h = h;
    this.funcion = funcion;
  }


  public float calcularRunge()  {
    float h = this.h;
    while (yInicial >=0) {
      double k1 = cambioCalculo(funcion, xInicial, yInicial);
      double k2 = k2(h, k1);
      double k3 = k3(h, k2);
      this.xInicial = pasoSiguiente(h, xInicial);
      double k4 = k4(h, k3);
      yInicial = (float) (yInicial + h / 6 * (k1 + 2 * k2 + 2 * k3 + k4));

    }
    return xInicial;
  }

  private double cambioCalculo(String funcion, float x, float y) {
    String funcionX_Y = funcion
            .replace("x",  String.valueOf(x))
            .replace("y", String.valueOf(y));
    return evaluarFuncion(funcionX_Y);

  }

  private float pasoSiguiente(float h, float x){
    return h + x;
  }

  private double evaluarFuncion(final String str) {
    return new Object() {
      int pos = -1, ch;

      void nextChar() {
        ch = (++pos < str.length()) ? str.charAt(pos) : -1;
      }

      boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
          nextChar();
          return true;
        }
        return false;
      }

      double parse() {
        nextChar();
        double x = parseExpression();
        if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
        return x;
      }

      double parseExpression() {
        double x = parseTerm();
        for (;;) {
          if      (eat('+')) x += parseTerm();
          else if (eat('-')) x -= parseTerm();
          else return x;
        }
      }

      double parseTerm() {
        double x = parseFactor();
        for (;;) {
          if      (eat('*')) x *= parseFactor();
          else if (eat('/')) x /= parseFactor();
          else return x;
        }
      }

      double parseFactor() {
        if (eat('+')) return +parseFactor();
        if (eat('-')) return -parseFactor();

        double x;
        int startPos = this.pos;
        if (eat('(')) {
          x = parseExpression();
          if (!eat(')')) throw new RuntimeException("Missing ')'");
        } else if ((ch >= '0' && ch <= '9') || ch == '.') {
          while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
          x = Double.parseDouble(str.substring(startPos, this.pos));
        } else if (ch >= 'a' && ch <= 'z') {
          while (ch >= 'a' && ch <= 'z') nextChar();
          String func = str.substring(startPos, this.pos);
          if (eat('(')) {
            x = parseExpression();
            if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
          } else {
            x = parseFactor();
          }
          x = switch (func) {
            case "sqrt" -> Math.sqrt(x);
            case "sin" -> Math.sin(Math.toRadians(x));
            case "cos" -> Math.cos(Math.toRadians(x));
            case "tan" -> Math.tan(Math.toRadians(x));
            default -> throw new RuntimeException("Unknown function: " + func);
          };
        } else {
          throw new RuntimeException("Unexpected: " + (char)ch);
        }

        if (eat('^')) x = Math.pow(x, parseFactor());

        return x;
      }
    }.parse();
  }

  private double k2(float h, double k1) {
    float xiCalculok2 = xInicial + (h / 2);
    float yiCalculok2 = (float) (yInicial + h / 2 * k1);
    return cambioCalculo(funcion, xiCalculok2, yiCalculok2);
  }

  private double k3(float h, double k2){
    float xiCalculok3 = xInicial + (h / 2);
    float yiCalculok3 = (float) (yInicial + h / 2 * k2);
    return cambioCalculo(funcion, xiCalculok3, yiCalculok3);
  }

  private double k4 (float h, double k4){
    float yiCalculok4 = (float) (yInicial + h * k4);
    return cambioCalculo(funcion, xInicial, yiCalculok4);
  }
}
