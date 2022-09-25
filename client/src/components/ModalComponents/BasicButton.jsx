import styled from "styled-components";

const BasicButton = styled.button`
  width: ${({ w = "80px" }) => w};
  height: ${({ h = "30px" }) => h};
  background: ${({ bg = "#1ba098" }) => bg};
  color: ${({ c = "white" }) => c};
  border-radius: 0.5rem;
  border: none;
  padding: 0.5rem;
  cursor: pointer;
  box-shadow: 0 0.25rem 0.25rem #0006;
  display: grid;
  place-content: center;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
  position: relative;
  &:active {
    transform: translateY(0.25rem);
    box-shadow: none;
  }
`;

export default BasicButton;
