import React, { useEffect, useState } from "react";

const WindowSizePrinter = ({props}) => {
  const [windowSize, setWindowSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight,
  });

    const [position, setPosition] = useState({x: 0, y: 0});


    const menuStyle = {
        position: "absolute",
        top: `${position.y + 10}px`,
        left: `${position.x + 10}px`,
        border: '1px solid black',

  };

  useEffect(() => {
    const handleResize = () => {
        console.log("ref print ", props.current.getBoundingClientRect());
        if (props.current) {
        const rect = props.current.getBoundingClientRect();
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

  return (
    <div>
      <p>Window Width: {windowSize.width}px</p>
      <p>Window Height: {windowSize.height}px</p>
      <div style={menuStyle} >
        <h1>experiment</h1>
      </div>
    </div>
  );
};

export default WindowSizePrinter;
