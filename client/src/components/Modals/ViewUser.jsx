import { useState } from "react";
import {
  StyledModal,
  StyledModalHeader,
  StyledModalBody,
  StyledCloseButton,
} from "./Modals.module";

import BasicButton from "../ModalComponents/BasicButton";
import styled from "styled-components";

const StyledUserButton = styled(BasicButton)`
  ${({ admin }) =>
    admin
      ? `::after {
    content: "*";
    position: absolute;
    left: 0.25rem;
    height: 100%
    font-size: 0.5rem;
    color: #0b2d45;
  }`
      : ""}
`;

const StyledUserInfo = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  gap: 1rem;
  padding: 1rem;
  div.info-row {
    display: flex;
    justify-content: space-evenly;
    width: 100%;
    gap: 2rem;
  }
  div.info-group {
    flex-grow: 1;
  }
  p {
    padding: 0;
    margin: 0;
  }
  p.info-label {
    font-size: 0.8rem;
    color: #1ba098;
    margin-left: 1rem;
  }
  p.info-field {
    border-bottom: 1px solid #deb992;
    text-align: center;
    height: 1.5rem;
  }
`;

const ViewUser = ({
  user: {
    username,
    profile: { firstName, lastName, email, phone },
    status,
    active,
    admin,
  },
  abbreviate,
}) => {
  const [modalOpen, setModalOpen] = useState(false);

  const fields = [
    { value: username, label: "username" },
    [
      { value: firstName, label: "first name" },
      { value: lastName, label: "last name" },
    ],
    { value: email, label: "email" },
    { value: phone, label: "phone" },
    [
      { value: status, label: "status" },
      { value: active ? "Yes" : "No", label: "active" },
      { value: admin ? "Yes" : "No", label: "admin" },
    ],
  ];

  const toggle = () => setModalOpen(!modalOpen);

  const displayName = (a) =>
    `${firstName} ${lastName ? (a ? `${lastName[0]}.` : lastName) : ""}`;

  const genGroup = (group, idx) =>
    Array.isArray(group) ? (
      group.map(genGroup)
    ) : (
      <div className="info-group" key={idx}>
        <p className="info-field">{group.value}</p>
        <p className="info-label">{group.label}</p>
      </div>
    );

  return (
    <>
      <StyledUserButton
        admin={admin}
        onClick={() => setModalOpen(true)}
        w="auto"
        bg={!active ? "#a7a7a7" : undefined}
      >
        {displayName(abbreviate)}
      </StyledUserButton>
      <StyledModal isOpen={modalOpen} toggle={toggle}>
        <StyledModalHeader>
          {displayName()}
          <StyledCloseButton color="danger" onClick={() => setModalOpen(false)}>
            X
          </StyledCloseButton>
        </StyledModalHeader>
        <StyledModalBody>
          <StyledUserInfo>
            {fields.map((field, idx) => (
              <div className="info-row" key={idx}>
                {genGroup(field)}
              </div>
            ))}
          </StyledUserInfo>
        </StyledModalBody>
      </StyledModal>
    </>
  );
};

export default ViewUser;
