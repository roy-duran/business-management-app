import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { FormGroup, Label } from "reactstrap";
import { Formik } from "formik";
import {
  StyledModal,
  StyledModalHeader,
  StyledModalBody,
  StyledButton,
  StyledField,
  StyledForm,
  StyledCloseButton,
  StyledAddTeam,
  StyledPlus,
  StyledText,
  NewButton,
} from "./Modals.module";
import { getCompany } from "./../../reducers/rootReducer";
import { createTeam } from "../../utils/requests";

const CreateTeam = ({ members, update }) => {
  const [modalOpen, setModalOpen] = useState(false);
  const [pickedMembers, setPickedMembers] = useState([]);
  const [newMembers, setNewMembers] = useState([]);
  const company = useSelector(getCompany);

  const toggle = () => {
    setModalOpen(!modalOpen);
  };

  const handleSubmit = (values) => {
    // pass the [values] to extract everything submited to the form
    const postMembers = [];
    pickedMembers.map((member) => postMembers.push(member.username));
    const postTeamData = {
      teamName: values.teamName,
      teamDescription: values.description,
      companyID: company.id,
      usernames: postMembers,
    };
    createTeam(postTeamData);
    setTimeout(update, 100);
    setPickedMembers([]);
    setNewMembers([]);
    setModalOpen(false);
  };

  useEffect(() => {
    setNewMembers([...members]);
  }, [members]);

  const removeMember = (member) => {
    setPickedMembers(pickedMembers.filter((user) => user !== member));
    setNewMembers([...newMembers, member]);
  };

  const setSelectChange = (e) => {
    const option = e.target.value;
    setPickedMembers([
      ...pickedMembers,
      newMembers.filter((member) => member.username === option)[0],
    ]);
    setNewMembers(newMembers.filter((member) => member.username !== option));
  };

  return (
    <>
      <NewButton outline onClick={() => setModalOpen(true)}>
        <StyledAddTeam>
          <StyledPlus>+</StyledPlus>
          <StyledText>New Team</StyledText>
        </StyledAddTeam>
      </NewButton>
      <StyledModal isOpen={modalOpen} toggle={toggle}>
        <StyledModalHeader>
          Create Team
          <StyledCloseButton color="danger" onClick={() => setModalOpen(false)}>
            X
          </StyledCloseButton>
        </StyledModalHeader>
        <StyledModalBody>
          <Formik
            initialValues={{
              teamName: "",
              description: "",
            }}
            onSubmit={handleSubmit}
          >
            <StyledForm>
              <FormGroup>
                <Label htmlFor="teamName"></Label>
                <StyledField
                  name="teamName"
                  placeholder="Team Name"
                  className="form-control"
                />
              </FormGroup>
              <FormGroup>
                <Label htmlFor="description"></Label>
                <StyledField
                  name="description"
                  placeholder="Description"
                  className="form-control"
                />
              </FormGroup>
              <FormGroup>
                <Label
                  htmlFor="members"
                  style={{ color: "rgb(222, 185, 146)", marginTop: "3rem" }}
                >
                  Members
                </Label>
                <select
                  id="members"
                  name="members"
                  onChange={setSelectChange}
                  className="form-control"
                >
                  <option>Pick A Member</option>
                  {newMembers?.map((user) => (
                    <option key={user.username} value={user.username}>
                      {user.profile.firstName}
                    </option>
                  ))}
                </select>
              </FormGroup>
              {pickedMembers
                ? pickedMembers.map((member, idx) => (
                    <div key={idx}>
                      <div>
                        {member.profile.firstName}
                        <button
                          type="button"
                          onClick={() => {
                            removeMember(member);
                          }}
                        >
                          x
                        </button>
                      </div>
                    </div>
                  ))
                : ""}
              <StyledButton type="submit" color="primary">
                Submit
              </StyledButton>
            </StyledForm>
          </Formik>
        </StyledModalBody>
      </StyledModal>
    </>
  );
};

export default CreateTeam;
