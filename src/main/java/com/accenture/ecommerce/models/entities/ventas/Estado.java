package com.accenture.ecommerce.models.entities.ventas;

public enum Estado {
    A_PAGAR {
        @Override
        public Estado getEstadoSiguiente() {
            return PAGADO;
        }
    },
    PAGADO {
        @Override
        public Estado getEstadoSiguiente() { return EN_CAMINO; }
    },
    EN_CAMINO {
        @Override
        public Estado getEstadoSiguiente() {
            return ENTREGADO;
        }
    },
    ENTREGADO {
        @Override
        public Estado getEstadoSiguiente() {
            return DEVUELTO;
        }
    },
    DEVUELTO {
        @Override
        public Estado getEstadoSiguiente() {
            return null;
        }
    };

    public abstract Estado getEstadoSiguiente();
}
