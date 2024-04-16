import React, { useEffect, useState } from 'react';
import './Menu.css';

const Menu = ({ closeHook, buttonRef, initialLoadRef}) => {

  const [hoverIndex, setHoverIndex] = useState(null);

  // const {x, y} = position;
  const [submenuPosition, setSubmenuPosition] = useState({x: 0, y: 0});
  const [position, setPosition] = useState({ x: 0, y: 0 });


  const handleMouseEnter = (index, event) => {
    const targetRect = event.target.getBoundingClientRect(); 
    const x = targetRect.right;
    const y = targetRect.top; 
    setHoverIndex(index);
    setSubmenuPosition({x: x, y: y});
  };

  const handleMouseLeave = () => {
    setTimeout(()=>{
      setHoverIndex(null);
    }, 700)
  };

  // useEffect(() => {
  //   console.log("position changed");
  // }, [buttonRef])


      useEffect(() => {
        const handleResize = () => {
          console.log("ref print ", buttonRef.current.getBoundingClientRect());
          if (buttonRef.current) {
            const rect = buttonRef.current.getBoundingClientRect();
            setPosition({ x: rect.left, y: rect.top });
          }
          // setWindowSize({
          //   width: window.innerWidth,
          //   height: window.innerHeight,
          // });
        };

        window.addEventListener("resize", handleResize);

        return () => {
          window.removeEventListener("resize", handleResize);
        };
      }, []);

  const menuStyle = {
    position: "absolute",
    top: position.x === 0 ? `${initialLoadRef.y}px` : `${position.y}px`,
    left: position.x === 0 ? `${initialLoadRef.x + 10}px` : `${position.x + 100}px`,
  };


  const arr = [
    {
      title: "apple",
      children: [],
    },
    {
      title: "veggies",
      children: [
        {
          title: "palak",
          children: [],
        },
        {
          title: "paneer",
          children: [],
        },
      ],
    },
    {
      title: "chilli",
      children: [],
    },
    {
      title: "chicken",
      children: [],
    },
  ];

  return (
    <div className="dropdown" 
    style={menuStyle}
    >
      {/*<h4>{initialLoadRef.x}</h4>*/}
      <ul className="menu-list">
        {arr.map((each, index) => (
          <li
            // onClick={closeHook}
            onMouseEnter={(e) => handleMouseEnter(index, e)}
            onMouseLeave={handleMouseLeave}
            style={{
              backgroundColor: hoverIndex === index ? "rgb(216, 216, 216)" : "",
            }}
            key={index}
            className="menu-item"
          >
            {each.title}
            {
             hoverIndex !== null &&
              (
              <div style={{marginLeft: '10px'}} className='submenu'>
                <h2>{"sdf"}</h2>
              </div>
             )
            }
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Menu;
