import { useSelector, useDispatch } from "react-redux";
import styled from "styled-components";
import { getAdmin, setTeam } from "../reducers/rootReducer";
import BasicButton from "./ModalComponents/BasicButton";
import CreateProject from "./Modals/CreateProject";
import ViewProject from "./Modals/ViewProject";
import { Redirect } from "react-router-dom";
import { useState } from "react";

const StyledProject = styled.div`
  display: flex;
  align-items: center;
  width: 70%;
  justify-content: space-between;
  flex-direction: row-reverse;
  padding-block: 2rem;
  :not(:last-child) {
    border-bottom: 0.0625rem solid #deb992;
  }
  div.project-title {
    display: flex;
    gap: 2rem;
    align-items: center;
    margin-bottom: 1rem;
    * {
      margin: 0;
    }
  }
  div.info-group p {
    font-size: 0.8rem;
    span.label {
      color: #1ba098;
    }
  }
  p.project-desc {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    width: fit-content;
    width: 50vw;
  }
  button {
    flex: none;
  }
`;

const Project = ({
  updatePage,
  name,
  editedDaysAgo,
  description,
  id,
  teamID,
  teamName,
}) => {
  const isAdmin = useSelector(getAdmin);
  const dispatch = useDispatch();

  return name !== undefined &&
    editedDaysAgo !== undefined &&
    description !== undefined ? (
    <StyledProject>
      {isAdmin ? (
        <CreateProject
          team={teamID}
          updatePage={updatePage}
          name={name}
          desc={description}
          projectID={id}
          buttonText="Edit"
        />
      ) : (
        <ViewProject
          team={teamID}
          name={name}
          desc={description}
          editedDaysAgo={editedDaysAgo}
          projectID={id}
        />
      )}
      <div className="project-row">
        <div className="project-title">
          <h2>{name}</h2>
          <div className="info-group">
            <p className="project-timestamp">
              <span className="label">Last edited: </span>
              {editedDaysAgo}
              <span className="label"> days ago.</span>
            </p>
            {isAdmin ? (
              <p className="project-assigned-team">
                <span className="label">Assigned Team:</span> {teamName}
              </p>
            ) : (
              ""
            )}
          </div>
        </div>
        <p className="project-desc">{description}</p>
      </div>
    </StyledProject>
  ) : (
    <StyledProject>
      <CreateProject updatePage={updatePage} buttonText="New" />
      <BasicButton
        w="250px"
        onClick={() => {
          dispatch(setTeam(""));
        }}
      >
        View All Company Projects
      </BasicButton>
    </StyledProject>
  );
};

export default Project;
