package com.moviecatalog.custom.structures.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviecatalog.custom.structures.DoublyLinkedListInter;
import com.moviecatalog.model.BaseModel;

public class DoublyLinkedList<T extends BaseModel<T>> implements DoublyLinkedListInter<T> {

    class Node {
        public T elemento;
        public Node anterior;
        public Node proximo;

        public Node(T elemento) {
            this.elemento = elemento;
            this.proximo = null;
            this.anterior = null;
        }

        public T getElemento() {
            return elemento;
        }

        public void setElemento(T elemento) {
            this.elemento = elemento;
        }

        public Node getAnterior() {
            return anterior;
        }

        public void setAnterior(Node anterior) {
            this.anterior = anterior;
        }

        public Node getProximo() {
            return proximo;
        }

        public void setProximo(Node proximo) {
            this.proximo = proximo;
        }
    }

    public Node inicio;
    public Node ultimo;public int tamanho;

    public DoublyLinkedList() {
        this.inicio = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    public boolean isEmpty() {
        return inicio == null;
    }

    public void pushFirst(T elemento) {
        Node lista = new Node(elemento);
        if (isEmpty()) {
            inicio = ultimo = lista;
        } else {
            inicio.setAnterior(lista);
            lista.setProximo(inicio);
            inicio = lista;
        }
        tamanho++;
    }

    public void pushLast(T elemento) {
        Node lista = new Node(elemento);
        if (isEmpty()) {
            inicio = ultimo = lista;
        } else {
            ultimo.setProximo(lista);
            lista.setAnterior(ultimo);
            ultimo = lista;
        }
        tamanho++;
    }

    public void pushAfter(T elemento) {
        if (searchPosition(elemento.getId()) != null) {
            Node listaAtual = inicio;
            Node anterior = null;
            while (listaAtual != null) {
                if (listaAtual.getElemento() == elemento) {
                    anterior = listaAtual;
                    break;
                }
                listaAtual = listaAtual.getProximo();
            }
            Node lista = new Node(elemento);
            lista.setElemento(elemento);
            lista.setProximo(listaAtual.getProximo());
            anterior.setProximo(lista);
            anterior.getProximo().setAnterior(anterior);
            System.out.println(anterior.getProximo().getAnterior().getElemento());
            if (ultimo.getProximo() != null) {
                ultimo = ultimo.getProximo();
            }
            tamanho++;
        } else {
            pushFirst(elemento);
        }
    }

    public T peekFirst() {
        Node listaAtual = inicio;
        if (isEmpty()) {
            return null;
        } else {
            return listaAtual.getElemento();
        }
    }

    public T peekLast() {
        Node listaAtual = ultimo;
        if (isEmpty()) {
            return null;
        } else {
            return listaAtual.getElemento();
        }
    }

    public T deleteFirst() {
        Node listaAtual = inicio;
        T conteudo = null;
        if (isEmpty()) {
            return null;
        } else {
            conteudo = listaAtual.getElemento();
            inicio = inicio.getProximo();
            inicio.setAnterior(null);
            tamanho--;
        }
        return conteudo;
    }

    public T deleteLast() {
        Node listaAtual = ultimo;
        T conteudo = null;
        if (isEmpty()) {
            return null;
        } else {
            conteudo = listaAtual.getElemento();
            ultimo = ultimo.getAnterior();
            ultimo.setProximo(null);
            tamanho--;
        }
        return conteudo;
    }

    public T delete(T elemento) {
        Node listaAtual = searchNode(elemento);
        T conteudo = null;
        if (isEmpty()) {
            return null;
        } else {
            if (listaAtual == inicio) {
                conteudo = listaAtual.getElemento();
                inicio = inicio.getProximo();
                inicio.setAnterior(null);
            } else {
                if (listaAtual == ultimo) {
                    conteudo = listaAtual.getElemento();
                    ultimo = ultimo.getAnterior();
                    ultimo.setProximo(null);
                } else {
                    conteudo = listaAtual.getElemento();
                    listaAtual.getAnterior().setProximo(listaAtual.getProximo());
                    listaAtual.getProximo().setAnterior(listaAtual.getAnterior());
                }
            }
            tamanho--;
        }
        return conteudo;
    }

    public Node searchNode(T elemento) {
        Node listaAtual = inicio;
        while (listaAtual != null) {
            if (listaAtual.getElemento() == elemento) {
                return listaAtual;
            }
            listaAtual = listaAtual.getProximo();
        }
        return null;
    }

    public T searchPosition(int id) {
        Node listaAtual = inicio;
        T conteudo = null;
        if (!isEmpty()) {
            while (listaAtual != null) {
                if (listaAtual.getElemento().getId() == id) {
                    conteudo = listaAtual.getElemento();
                }
                listaAtual = listaAtual.getProximo();
            }
            return conteudo;
        } else {
            return null;
        }
    }

//    public String toString() {
//        String saida = "";
//        Node listaAtual = inicio;
//        while (listaAtual != null) {
//            saida += "[" + "ID=" + listaAtual.getId() + ", Nome=" + listaAtual.getElemento() + "] ";
//            listaAtual = listaAtual.getProximo();
//        }
//        return saida;
//    }


    @Override
    public String toString() {

        String result = "";

        Node ref = inicio;

        while (ref != null) {

            result += "{value: " + ref.elemento + "},\n ";

            ref = ref.proximo;

        }

        if (result.equals("")) {

            return "[]";

        }

        result = result.substring(0, result.length() - 3);

        return "[" + result + "]";
    }

    public Object formatToJSONObject() throws JsonMappingException, JsonProcessingException {

        String result = "";

        Node ref = inicio;

        while (ref != null) {

            result += ref.elemento.toJSONString() + ",";

            ref = ref.proximo;

        }

        if (result.equals("")) {

            return "[]";

        }

        result = result.substring(0, result.length() - 1);

        result = "[" + result + "]";

        Object obj = new ObjectMapper().readValue(result, Object.class);

        return obj;

    }
}
