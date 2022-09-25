import { Button, Modal, ModalHeader, ModalBody } from "reactstrap";
import { Field, Form } from "formik";

import styled from "styled-components";

export const StyledModal = styled(Modal)`
  background: rgb(11, 45, 69);
  border-radius: 6px;
  position: relative;
`;
export const StyledModalHeader = styled(ModalHeader)`
  background: rgb(11, 45, 69);
  color: rgb(222, 185, 146);
  border: none;
  display: grid;
  place-content: center;
`;
export const StyledModalBody = styled(ModalBody)`
  background: rgb(11, 45, 69);
`;
export const StyledButton = styled(Button)`
  background: rgb(27, 160, 152);
`;
export const StyledField = styled(Field)`
  color: rgb(222, 185, 146);
  background: rgb(11, 45, 69);
  border: none;
  width: 75%;
  border-radius: 0;
  border-bottom: 1px solid rgb(222, 185, 146);
  &::placeholder {
    color: rgb(222, 185, 146);
  }
  &:focus {
    background: rgb(11, 45, 69);
    color: rgb(222, 185, 146);
  }
  ::-webkit-scrollbar {
    display: none;
  }
  margin: auto;
`;

export const StyledForm = styled(Form)`
  position: relative;
  text-align: center;
`;

export const StyledCloseButton = styled(Button)`
  background: no-repeat scroll 0 0 transparent;
  color: red;
  font-weight: bold;
  border: 3px solid red;
  border-radius: 50px;
  position: absolute;
  right: 0.5rem;
  top: 0.5rem;
`;

export const NewButton = styled(Button)`
  z-index: 1;
  margin: auto;
  width: 100%;
  height: 21rem;
  border: none;
  &:hover {
    background: none;
  }
  &:active {
    background: none;
    border: none;
  }
  &:focus {
    background: none;
    border: none;
  }
`;

export const StyledAddTeam = styled.div`
  position: relative;
  width: 100%;
  height: 20rem;
  border: 3px solid rgb(209, 175, 139);
  border-radius: 25px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;
export const StyledPlus = styled.div`
  color: rgb(222, 185, 146);
  position: absolute;
  font-size: 20rem;
  bottom: -10%;
`;

export const StyledText = styled.div`
  font-size: 2rem;
  color: rgb(222, 185, 146);
  position: absolute;
  bottom: 10%;
`;
