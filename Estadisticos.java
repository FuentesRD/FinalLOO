package VCódigo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author ddfru
 */

public class Estadisticos {

    private ArrayList <Integer> datos;
    private int numDatos;
    
    public Estadisticos(ArrayList<Integer> arr, int dim){
        datos = new ArrayList<>();
        Random numero = new Random();
        if(arr.size() == 1){
            arr.clear();
            for(int i=0; i<dim; i++){
                datos.add(numero.nextInt(0,100));
            } 
        } else {
            for(Integer num : arr){
                datos.add(num);
            }
        }
        
        ordenar(datos);
        this.numDatos = datos.size(); //Linea agregada por IA
    }
    
    
    private void ordenar(ArrayList<Integer> lista1){
        Collections.sort(lista1);
    }
    
    private int sumar(){
        int resultado=0;
        for (Integer num : datos){
            resultado += num;
        }
        return resultado;
    }
    
  
    public int getNumDatos(){
        return this.numDatos;
    }
    
    public ArrayList<Integer> getDatos(){
        return this.datos;
    }
    
    public int minimo(){
        return datos.getFirst();
    }
    
    public int maximo(){
    return datos.getLast();
    }
    
    public int rango(){
        return maximo()-minimo();
    }
    
    public double cuartil1(){
        //i * p numDatos /100
        double cuartil;
        cuartil = 25.0 * numDatos / 100; //Calculo de "i"
        if(cuartil %1 == 0){
            double valorAct = datos.get((int)cuartil);
            double valorAnt = datos.get((int)cuartil-1);
            return (valorAct + valorAnt) / 2.0;
        } else {
            return datos.get((int)cuartil);
        }
    }
    
    public double cuartil2(){
        double cuartil;
        cuartil = 50.0 * numDatos / 100;
        if(cuartil %1 == 0){
            double valorAct = datos.get((int)cuartil);
            double valorAnt = datos.get((int)cuartil-1);
            return (valorAct + valorAnt) / 2.0;
        } else {
            return datos.get((int)cuartil);
        }
    }
    
    public double cuartil3(){
        double cuartil;
        cuartil = 75.0 * numDatos / 100;
        if(cuartil %1 == 0){
            double valorAct = datos.get((int)cuartil);
            double valorAnt = datos.get((int)cuartil-1);
            return (valorAct + valorAnt) / 2.0;
        } else {
            return datos.get((int)cuartil);
        }
    }
    
    public double ric(){
        return (double) cuartil3() - cuartil1();
    }
    
    public double media(){
        return (double) sumar() / getNumDatos();
    }
    
    public double varianza(){
/*        
    1 Suma todos los datos y dividir entre la cantidad de datos (media)
    2 Restar la media a cada dato
    3 Elevar al cuadrado cada resultado
    4 Dividir entre n-1 la suma de cada resultado
*/         
        double med = media();
        ArrayList<Double> restas = new ArrayList<>();
        for(Integer num : datos){
            restas.add(num-med);
        }
        
        for(int i=0; i<restas.size();i++){
            restas.set(i, (restas.get(i)*restas.get(i)));
        }
        
        Double suma = 0.0;
        for(int i=0; i<restas.size();i++){
            suma += restas.get(i);
        }
        
        return suma / (getNumDatos() - 1);
    }
    
    public double desvStd(){
        return Math.sqrt(varianza());
    }
    
    public double coefVar(){
        return desvStd() / media() * 100;
    }
    
    
    @Override
    public String toString(){
        String cadena="";
        for(int i=0; i<datos.size();i++){
          cadena += datos.get(i) + " ";
        }
        return cadena;
    }
    
    }