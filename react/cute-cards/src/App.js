import logo from './logo.svg';
import './App.css';
import {Card} from "./Card";
import {cardData} from "./data";

function App() {

  return (
    <div className="App">
        <div className="card-container">
            {
                cardData.map((card) => {
                    return <Card key={card.id}
                                 title={card.title}
                                 body={card.body}
                                 buttonText={card.buttonText} />
                })
            }
        </div>
    </div>
  );
}

export default App;
