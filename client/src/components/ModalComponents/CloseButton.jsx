import styled from "styled-components";

const StyledClose = styled.button`
  width: ${({ w }) => (w ? w : "38px")};
  height: ${({ h }) => (h ? h : "38px")};
  color: white;
  font-size: 1.1rem;
  border: none;
  background: rgb(220,53,69);
  border-radius: 5px;
`;

const CloseButton = () => {
  return <StyledClose>X</StyledClose>;
};
export default CloseButton;
