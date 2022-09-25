import styled from "styled-components";

export const Card = styled.div`
  border-radius: 8px;
  width: 50vw;
  background: #0b2d45;
  z-index: -1;
  & > div {
    margin: 0 50px;
    border: 0;
  }
  & p {
    margin: 0;
  }
  & div.card-header {
    display: flex;
    justify-content: space-between;
  }
  & h2.title {
    font-size: 1.6rem;
    margin-bottom: 1rem;
    color: #1ba098;
  }
  & p.message {
    font-size: 0.8rem;
  }
`;

const AnnouncementCard = (props) => {
  return (
    <Card className="card">
      <div className="card-header">
        <p className="author">{`${props.firstName} ${props.lastName}`}</p>
        <p className="date-posted">{new Date(props.postDate).toDateString()}</p>
      </div>
      <div className="card-body">
        <h2 className="title">{props.title}</h2>
        <p className="message">{props.message}</p>
      </div>
    </Card>
  );
};

export default AnnouncementCard;
