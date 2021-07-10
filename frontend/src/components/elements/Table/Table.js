import React, { useState, useEffect, useRef } from "react";
import styled from "styled-components";

import Input from "../Input/Input";

/* TODO: column width */
const Root = styled.table`
  width: 100%;
  margin-top: 2vh;

  tr.last {
    border-bottom: 1px solid black;
  }
`;

const TBody = styled.tbody``;
const THead = styled.thead``;

const TableRow = styled.tr`
  text-align: center;
`;

const TableHead = styled.th`
  border-bottom: 1px solid black;
`;

const TableData = styled.td`
  height: ${(props) => (props.tdHeight ? props.tdHeight : "60px")};
  padding: 10px;
  text-align: center;
  vertical-align: middle;
`;

const TableButton = styled.button`
  background-color: ${(props) => props.buttonColor};
  border: transparent;
  border-radius: 15px;
  height: 30px;
  cursor: pointer;
  width: 100px;
`;

const Table = ({
  buttonColor,
  buttonLabel,
  data,
  headers,
  inputField,
  inputMatrix,
  labels,
  onBlur,
  onClick,
  subParentHeader,
  subLabels,
  subItemsLabel,
  tdHeight,
}) => {
  const buttonStyleProps = {
    buttonColor: buttonColor,
  };
  const tdStyleProps = {
    tdHeight: tdHeight,
  };

  const handleOnClick = (event) => {
    const { form } = event.target;
    const input = form[0];
    const { max, min, name, value } = input;

    /* TODO: warning message instead of doing nothing */
    if (max && parseFloat(value) > parseFloat(max)) {
      return;
    }

    if (min && parseFloat(value) < parseFloat(min)) {
      return;
    }

    onBlur({
      name: name,
      value: value,
    });
  };

  return (
    <Root>
      <THead>
        <TableRow>
          {headers.map((header, index) => (
            <TableHead
              key={`${header}-${index}-${Math.random()}`}
              colSpan={
                subParentHeader &&
                header === subParentHeader &&
                subLabels.length
              }
            >
              {header}
            </TableHead>
          ))}
        </TableRow>
        {subParentHeader && (
          <TableRow>
            {headers.map((header, headerIndex) =>
              header === subParentHeader ? (
                subLabels.map((subLabel, subLabelIndex) => (
                  <TableHead
                    key={`${subLabel}-${subLabelIndex}-${Math.random()}`}
                  >
                    {subLabel}
                  </TableHead>
                ))
              ) : (
                <TableHead key={`${header}-${headerIndex}-${Math.random()}`}>
                  {""}
                </TableHead>
              )
            )}
          </TableRow>
        )}
      </THead>
      <TBody>
        {data.map((item, itemIndex) => {
          // const subItemLabel = subLabelHeader;
          const subItems = subItemsLabel && item[subItemsLabel];
          console.log(subItems);
          // if nested data exist
          if (subItems) {
            return subItems.map((subItem, subItemIndex) => {
              if (subItemIndex === 0) {
                return (
                  <TableRow
                    key={`${subItem}-${itemIndex}-${Math.random()}`}
                    className={
                      subItemIndex === subItems.length - 1 ? "last" : ""
                    }
                  >
                    {labels.map((label, labelIndex) => {
                      if (label !== subItemsLabel) {
                        return (
                          <TableData
                            {...tdStyleProps}
                            key={`${label}-${labelIndex}-${Math.random()}`}
                            rowSpan={subItems.length}
                          >
                            {item[label]}
                          </TableData>
                        );
                      } else {
                        return subLabels.map((subLabel, subLabelIndex) => (
                          <TableData
                            key={`${subLabel}-${subLabelIndex}-${Math.random()}`}
                          >
                            {subItem[subLabel]}
                          </TableData>
                        ));
                      }
                    })}
                  </TableRow>
                );
              } else {
                return (
                  <TableRow
                    key={`${item}-${itemIndex}-${Math.random()}`}
                    className={
                      subItemIndex === subItems.length - 1 ? "last" : ""
                    }
                  >
                    {subLabels.map((subLabel, subLabelIndex) => (
                      <TableData
                        key={`${subLabel}-${subLabelIndex}-${Math.random()}`}
                      >
                        {subItem[subLabel]}
                      </TableData>
                    ))}
                  </TableRow>
                );
              }
            });
          } else {
            return (
              <TableRow key={`${item}-${itemIndex}-${Math.random()}`}>
                {labels.map((label, labelIndex) => (
                  <TableData
                    {...tdStyleProps}
                    key={`${item[label]}-${label}-${labelIndex}}`}
                  >
                    {item[label]}
                  </TableData>
                ))}
                {item.name && inputField && (
                  <TableData {...tdStyleProps}>
                    <form
                      id={`form-${item.name}`}
                      key={`form-${item.name}-${itemIndex}`}
                    >
                      <Input
                        inputMatrix={inputMatrix}
                        key={`${item.name}-${itemIndex}`}
                        min="0"
                        max="999"
                        name={item.name}
                        placeholder="0 - 999"
                        step={1}
                        type="number"
                      />
                    </form>
                  </TableData>
                )}
                {item.name && buttonLabel && (
                  <TableData {...tdStyleProps}>
                    <TableButton
                      key={`button-${item.name}-${itemIndex}`}
                      type="button"
                      form={inputField ? `form-${item.name}` : null}
                      {...buttonStyleProps}
                      onClick={
                        buttonLabel !== "Remove" && onBlur
                          ? (e) => handleOnClick(e)
                          : () => onClick({ itemIndex })
                      }
                    >
                      {buttonLabel}
                    </TableButton>
                  </TableData>
                )}
              </TableRow>
            );
          }
        })}
      </TBody>
    </Root>
  );
};

export default Table;
