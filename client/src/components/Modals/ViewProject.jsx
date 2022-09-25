import { useState } from "react";
import styled from "styled-components";
import BasicButton from "../ModalComponents/BasicButton";

import {
  StyledModal,
  StyledModalHeader,
  StyledModalBody,
  StyledCloseButton,
} from "./Modals.module";

const ModalTimestamp = styled.p`
  font-size: 0.8rem;
  text-align: right;
  margin: 0;
`;

const ViewProject = ({ name, desc, editedDaysAgo }) => {
  const [modalOpen, setModalOpen] = useState(false);

  const toggle = () => setModalOpen(!modalOpen);
  return (
    <>
      <BasicButton onClick={() => setModalOpen(true)} bg="#deb992" c="black">
        View
      </BasicButton>
      <StyledModal isOpen={modalOpen} toggle={toggle}>
        <StyledModalHeader>
          {name}
          <StyledCloseButton color="danger" onClick={() => setModalOpen(false)}>
            X
          </StyledCloseButton>
        </StyledModalHeader>
        <StyledModalBody>
          <p>{desc}</p>
          <ModalTimestamp>Last edited {editedDaysAgo} days ago</ModalTimestamp>
        </StyledModalBody>
      </StyledModal>
    </>
  );
};

export default ViewProject;
