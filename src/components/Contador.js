'use client'

import { useEffect, useState } from "react";

function Contador() {

    const [contador, setContador] = useState(0)
  
    const handleClick = () => {
      setContador(contador + 1)
    }
  
    useEffect(() => {
      console.log(`Mi contador ${contador}`)
    },[contador])
  
    return (
      <div>
        <p>El contador esta en: {contador}</p>
        <button onClick={handleClick} >incrementar</button>
      </div>
    );
  }

export default Contador;