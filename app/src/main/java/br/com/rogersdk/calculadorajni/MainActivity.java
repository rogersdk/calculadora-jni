package br.com.rogersdk.calculadorajni;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.logging.Logger;

import br.com.rogersdk.calculadorajni.databinding.ActivityMainBinding;
import br.com.rogersdk.calculadorajni.databinding.NumberBinding;
import br.com.rogersdk.calculadorajni.databinding.OperatorBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        GridView numbersGridView = binding.gridNumbers;
//        GridView operatorsGridView = binding.gridOperators;

        Button sumButton = binding.btnSum;
        Button minusButton = binding.btnMinus;
        Button divideButton = binding.btnDivide;
        Button multiplyButton = binding.btnMultiply;
        Button resultButton = binding.btnResult;

        OperacaoTO operacaoTO = new OperacaoTO();

        TextView xTextView = binding.x;
        TextView yTextView = binding.y;
        TextView operatorTextView = binding.operator;
        TextView resultadoTextView = binding.resultado;

        numbersGridView.setAdapter(new NumberAdapter(Arrays.asList("7", "8", "9", "4", "5", "6", "1", "2", "3", "", "0", "")));

        numbersGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String value = (String) numbersGridView.getItemAtPosition(position);

                if(operacaoTO.getOperador() == null) {
                    xTextView.setText(xTextView.getText().toString() + value);
                } else {
                    yTextView.setText(yTextView.getText().toString() + value);
                }
            }
        });
        
        sumButton.setOnClickListener(new View.OnClickListener() {
            int x = 0;

            @Override
            public void onClick(View view) {
                operacaoTO.setOperador(TipoOperacao.SUM);

                try {
                    x = Integer.parseInt(xTextView.getText().toString());
                } catch (NumberFormatException nfe) {
                    Log.w("MainActivity", String.format("valor X invalido, utilizando 0") );
                }

                operacaoTO.setX(x);
                operatorTextView.setText(operacaoTO.getOperador().getDescricao());
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            int x = 0;

            @Override
            public void onClick(View view) {
                operacaoTO.setOperador(TipoOperacao.MINUS);

                try {
                    x = Integer.parseInt(xTextView.getText().toString());
                } catch (NumberFormatException nfe) {
                    Log.w("MainActivity", String.format("valor X invalido, utilizando 0") );
                }

                operacaoTO.setX(x);
                operatorTextView.setText(operacaoTO.getOperador().getDescricao());
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            int x = 0;

            @Override
            public void onClick(View view) {
                operacaoTO.setOperador(TipoOperacao.DIVIDE);

                try {
                    x = Integer.parseInt(xTextView.getText().toString());
                } catch (NumberFormatException nfe) {
                    Log.w("MainActivity", String.format("valor X invalido, utilizando 0") );
                }

                operacaoTO.setX(x);
                operatorTextView.setText(operacaoTO.getOperador().getDescricao());
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            int x = 0;

            @Override
            public void onClick(View view) {
                operacaoTO.setOperador(TipoOperacao.MULTIPLY);

                try {
                    x = Integer.parseInt(xTextView.getText().toString());
                } catch (NumberFormatException nfe) {
                    Log.w("MainActivity", String.format("valor X invalido, utilizando 0") );
                }

                operacaoTO.setX(x);
                operatorTextView.setText(operacaoTO.getOperador().getDescricao());
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            int x = 0, y = 0;

            @Override
            public void onClick(View view) {
                try {
                    x = Integer.parseInt(xTextView.getText().toString());
                } catch (NumberFormatException nfe) {
                    Log.w("MainActivity", String.format("valor X invalido, utilizando 0") );
                }

                try {
                    y = Integer.parseInt(yTextView.getText().toString());
                } catch (NumberFormatException nfe) {
                    Log.w("MainActivity", String.format("valor Y invalido, utilizando 0") );
                }

                operacaoTO.setY(x);

                switch (operacaoTO.getOperador()) {
                    case SUM:
                        operacaoTO.setResultado(sum(x,y));
                        break;
                    case MINUS:
                        operacaoTO.setResultado(minus(x,y));
                        break;
                    case DIVIDE:
                        operacaoTO.setResultado(divide(x,y));
                        break;
                    case MULTIPLY:
                        operacaoTO.setResultado(multiply(x,y));
                        break;
                }

                resultadoTextView.setText(String.valueOf(operacaoTO.getResultado()));

                operacaoTO.setX(0);
                operacaoTO.setY(0);
                operacaoTO.setResultado(0);
                operacaoTO.setOperador(null);

                xTextView.setText("");
                yTextView.setText("");
                operatorTextView.setText("");
            }
        });

//        operatorsGridView.setAdapter(new NumberAdapter(Arrays.asList("+", "-", "÷", "x", "=")));

        /*operatorsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String value = (String) operatorsGridView.getItemAtPosition(position);

                String operator = operatorTextView.getText() != null ? operatorTextView.getText().toString() : null;

                int x = 0, y = 0;

                try {
                    x = Integer.parseInt(xTextView.getText().toString());
                }catch (NumberFormatException nfe) {
                    Log.w("MainActivity", String.format("valor X invalido, utilizando 0") );
                }

                operacaoTO.setX(x);

                try {
                    y = Integer.parseInt(yTextView.getText().toString());
                }catch (NumberFormatException nfe) {
                    Log.w("MainActivity", String.format("valor Y invalido, utilizando 0") );
                }

                operacaoTO.setY(y);

                if (operator != null) {
                    operacaoTO.setOperador(TipoOperacao.obterPorSimbolo(operator));
                }

                int resultadoLib = 0;

                if ("=".equals(value)) {

                    switch (operacaoTO.getOperador()) {
                        case SUM:
                            resultadoLib = sum(x, y);
                            break;
                        case MINUS:
                            resultadoLib = minus(x, y);
                            break;
                        case DIVIDE:
                            resultadoLib = divide(x, y);
                            break;
                        case MULTIPLY:
                            resultadoLib = multiply(x, y);
                            break;
                    }

                    resultadoTextView.setText(String.valueOf(resultadoLib));
                    operacaoTO.setOperador(null);
                }

                operatorTextView.setText(value);
            }
        });*/

        setContentView(binding.getRoot());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native int sum(int x, int y);

    public native int minus(int x, int y);

    public native int divide(int x, int y);

    public native int multiply(int x, int y);
}