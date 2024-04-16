import { useEffect, useRef, useState } from 'react';
import './App.css';
import Menu from './Menu';

function App() {

  const [open, setOpen] = useState(false);
  const [position, setPosition] = useState({x: 0, y: 0});
  const buttonRef = useRef(null);

  function debounce(fn, ms) {
          console.log("running");

  let timer;
  return () => {
    clearTimeout(timer);
    timer = setTimeout(() => {
      timer = null;
      fn.apply(this, arguments);
    }, ms);
  };
}

function App() {
  // ...your state declarations

    const [windowSize, setWindowSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight,
  });

  const buttonRef = useRef(null);


    useEffect(() => {
    const handleResize = () => {
        console.log("ref print ", buttonRef.current.getBoundingClientRect());
        if (buttonRef.current) {
        const rect = buttonRef.current.getBoundingClientRect();
        setPosition({ x: rect.left, y: rect.top });
      }
      setWindowSize({
        width: window.innerWidth,
        height: window.innerHeight,
      });
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  // ...rest of your component
}

  const handleClick = (event) => {
     if(buttonRef.current) {

         const targetRect = buttonRef.current.getBoundingClientRect();
        const x = targetRect.right; 
        const y = targetRect.top; 
        setPosition({x: x, y: y})
     }
    // const menuWidth = 200;

    setOpen(prevState => !prevState);
  }

  const handleClose = () => {
    setOpen(false);
  }

  return (
    
    <div className="App">
      <h1>{position.x}</h1>
        <h1>{buttonRef?.current?.getBoundingClientRect()?.right || "0"}</h1>
      <button ref={buttonRef} className='btn' onClick={(e) => handleClick(e)}>clickme</button>
       {
         open && <Menu  closeHook = {handleClose} initialLoadRef={position} buttonRef={buttonRef}></Menu>
       }
    </div>
    // <>
    //     <WindowSizePrinter props={buttonRef} />
    //    <button ref={buttonRef} className='btn' onClick={(e) => handleClick(e)}>clickme</button>
    // </>
  );
}

export default App;
