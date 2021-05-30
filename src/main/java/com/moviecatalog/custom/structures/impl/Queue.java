package com.moviecatalog.custom.structures.impl;

import com.moviecatalog.custom.structures.QueueInter;

public class Queue<T> implements QueueInter<T> {
        private Object[] elementos;
        private int inicio;
        private int fim;
        private int tam = 1000;
        private int quant;

        public Queue() {
            this.elementos = new Object[tam];
            this.inicio = 0;
            this.fim = -1;
            this.quant = 0;
        }

        public boolean isEmpty() {
            if (quant == 0) {
                System.out.println("Fila Vazia!");
                return true;
            } else {
                return false;
            }
        }

        public boolean isFull() {
            if (quant == tam) {
                System.out.println("Fila Cheia!");
                return true;
            } else {
                return false;
            }
        }

        public void enqueue(T elemento) {
            if ((elemento != null) && (!isFull())) {
                if (fim == tam - 1) {
                    fim = -1;
                }
                fim++;
                this.elementos[fim] = elemento;
                this.quant++;
            }
        }

        @SuppressWarnings("unchecked")
		public T dequeue() {
            T saida = null;
            if (!isEmpty()) {
                saida = (T) elementos[inicio];
                elementos[inicio] = "";
                inicio++;
                if (inicio == tam) {
                    inicio = 0;
                }
                quant--;
            }
            return saida;
        }

        @SuppressWarnings("unchecked")
		public T peek() {
            if (isEmpty()) {
                return null;
            }
            return (T) this.elementos[this.inicio];
        }

        public int size(){
            return quant;
        }
    }
