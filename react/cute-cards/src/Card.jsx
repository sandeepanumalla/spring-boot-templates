import './Card.css';

export const Card = ({ image, title, body, buttonText }) => {
    return (
        <div className="card">
            <div className="card-profile-section">
                {/* Render image */}
                <img src={image} alt="Profile" />
            </div>
            <div className="card-body-section">
                {/* Render title */}
                <div className="card-title">
                    <h1>{title}</h1>
                </div>
                {/* Render body */}
                <div className="card-body">{body}</div>
                {/* Render button */}
                <div className="card-button">
                    <button>{buttonText}</button>
                </div>
            </div>
        </div>
    );
};
